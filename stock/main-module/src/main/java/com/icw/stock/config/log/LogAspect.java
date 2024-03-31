package com.icw.stock.config.log;

import com.icw.stock.entity.Audit;
import com.icw.stock.entity.AuditError;
import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.repository.AuditErrorRepository;
import com.icw.stock.repository.AuditRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LogAspect {
	private final AuditRepository auditRepository;
	private final AuditErrorRepository auditErrorRepository;

	private ThreadLocal<UUID> requestIdHolder = new ThreadLocal<>();

	public String getRequestId() {
		if (!ObjectUtils.isEmpty(requestIdHolder.get())) {
			return requestIdHolder.get().toString();
		}
		return null;
	}

	@Autowired
	public LogAspect(AuditRepository auditRepository, AuditErrorRepository auditErrorRepository) {
		this.auditRepository = auditRepository;
		this.auditErrorRepository = auditErrorRepository;
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() {
	}

	// Request 로깅
	@Before("controller()")
	public void logBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		String requestUri = request.getRequestURI();
		String[] requestUriArr = request.getRequestURI().split("/");
		String dataId = requestUriArr[requestUriArr.length - 1];
		String method = request.getMethod();
		String body = Arrays.toString(joinPoint.getArgs());
		UUID requestId = generateRequestId();
		requestIdHolder.set(requestId);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("requestId", requestId.toString());
		jsonObject.put("requestUri", requestUri);
		jsonObject.put("dataId", dataId);
		jsonObject.put("method", method);
		jsonObject.put("data", body);

		log.info(jsonObject.toJSONString().replace("\\", ""));

		// Audit 정보 수집
		log.info("[LogAspect], method: logBefore(), msg: collect request-audit-info");
		String clientIp = getClientIP(request);
		String apiDesc = getApiDesc(joinPoint);

		try{
			Audit audit = Audit.builder()
					.uuid(requestId.toString())
					.reqUri(requestUri)
					.reqMethod(method)
					.reqDate(LocalDateTime.now())
					.clientIp(clientIp)
					.apiDesc(apiDesc)
					.build();

			auditRepository.save(audit);
		} catch (Exception e){
			log.error("Audit information saving error", e);
		}
	}

	private String getClientIP(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");

		// proxy server나 LB에서 클라이언트의 IP를 숨기려 할 때, unknown을 사용한다
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}

		return ipAddress;
	}

	private String getApiDesc(JoinPoint joinPoint) {
		String apiDesc = null;
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		if (method.isAnnotationPresent(Operation.class)) {
			Operation apiOperation = method.getAnnotation(Operation.class);
			apiDesc = apiOperation.description();
		}

		return apiDesc;
	}

	// Response 로깅
	@AfterReturning(pointcut = "controller()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String requestUri = request.getRequestURI();
		String[] requestUriArr = request.getRequestURI().split("/");
		String dataId = requestUriArr[requestUriArr.length - 1];
		UUID requestId = requestIdHolder.get();

		if (!(result instanceof ResponseEntity<?>)) return;

		ResponseEntity responseEntity = (ResponseEntity) result;
		int statusCode = responseEntity.getStatusCodeValue();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestId", requestId.toString());
		jsonObject.put("requestUri", requestUri);
		jsonObject.put("dataId", dataId);

		// Audit 응답 정보 업데이트
		try{
			Optional<Audit> optAuditEntity = auditRepository.findByUuid(requestId.toString());
			if (optAuditEntity.isPresent()) {
				Audit audit = optAuditEntity.get();
				audit.setResStatusCode(statusCode);
				audit.setResDate(LocalDateTime.now());

				auditRepository.save(audit);

				log.info("[LogAspect], method: logAfterReturning(), msg: collect response-audit-info");
			}
		}catch (Exception e){
			log.error("audit update error", e);
		}

		if (statusCode == HttpStatus.OK.value()) {
			// 예외처리, Swagger 접속 시 추후 처리 어떤방식으로할지 작업 필요
			if (responseEntity.getBody().getClass().equals(CommonResponse.ResSuccessPattern.class)) {
				CommonResponse.ResSuccessPattern resSuccessPattern = (CommonResponse.ResSuccessPattern) responseEntity.getBody();
				boolean success = resSuccessPattern.isSuccess();
				Object data = resSuccessPattern.getData();

				jsonObject.put("success", success);
				jsonObject.put("data", data);
			} else {
				jsonObject.put("success", true);
				jsonObject.put("data", "data");
			}
		} else {
			CommonResponse.ResFailPattern resFailPattern = (CommonResponse.ResFailPattern) responseEntity.getBody();
			boolean success = resFailPattern.isSuccess();
			String errorMsg = resFailPattern.getMessage();

			jsonObject.put("success", success);
			jsonObject.put("errorMsg", errorMsg);
		}

		log.info(jsonObject.toJSONString().replace("\\", ""));
	}

	//transaction 사용시 로깅
	@Around("execution(@org.springframework.transaction.annotation.Transactional * *(..))")
	public Object transactionLogger(ProceedingJoinPoint pjp) throws Throwable {
		StringBuilder sb = getParameters(pjp);
		log.info("Transaction call - location : {}, param : {}", pjp, sb);
		Object result = pjp.proceed();
		return result;
	}

	//특정경로 exception 로깅
	@AfterThrowing(
			pointcut = "execution(* com.icw.stock.repository.*.*(..)) || execution(* com.icw.stock.service.*.*(..)) ",
			throwing = "e"
	)
	public void afterThrowing(JoinPoint jp, Exception e) {
		StringBuilder sb = getParameters(jp);
		log.error("location : {}, param : {}", jp, sb, e);
	}

	private StringBuilder getParameters(JoinPoint _jp) {
		Object[] args = _jp.getArgs();
		StringBuilder sb = new StringBuilder();
		if (args != null) {
			for (Object arg : args) {
				if (arg != null) {
					if (sb.length() > 0) {
						sb.append(",");
					}
					sb.append(arg.toString());
				}
			}
		}
		return sb;
	}

	private UUID generateRequestId() {
		return UUID.randomUUID();
	}

	@AfterReturning(pointcut = "execution(* com.okestro.vista.portal.config.exception..*ExceptionHandler.*(..))", returning = "result")
	public void collectAuditError(Object result) {
		log.info("[LogAspect], method: collectAuditError(), msg: collect auditError");

		if (!(result instanceof ResponseEntity<?>)) return;
		if(ObjectUtils.isEmpty(requestIdHolder.get())) throw new NullPointerException("request UUID is null");

		ResponseEntity responseEntity = (ResponseEntity) result;
		String requestId = requestIdHolder.get().toString();
		String message = ((CommonResponse.ResFailPattern) responseEntity.getBody()).getMessage();

		Audit audit = null;
		try{
			Optional<Audit> optAuditEntity = auditRepository.findByUuid(requestId);
			if (optAuditEntity.isPresent()) {
				audit = optAuditEntity.get();
				audit.setResStatusCode(responseEntity.getStatusCodeValue());
				audit.setResDate(LocalDateTime.now());

				auditRepository.save(audit);
			}
		}catch (Exception e){
			log.error("When handling CollectAuditError(), audit save error", e);
		}

		try{
			AuditError auditError = AuditError.builder()
					.uuid(requestId)
					.errorMessage(message)
					.errorStatusCode(responseEntity.getStatusCodeValue())
					.createdDate(LocalDateTime.now())
					.audit(audit)
					.build();

			auditErrorRepository.save(auditError);
		}catch (Exception e){
			log.error("When handling CollectAuditError(), AuditError save error", e);
		}
	}
}
