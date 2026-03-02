package com.icw.stock.config.exception;

import com.icw.stock.common.IncomingWebhook;
import com.icw.stock.config.log.LogAspect;
import com.icw.stock.model.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends CommonResponse {
	private final ApplicationContext context;
	private final LogAspect logAspect;

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handle(Exception e) {
		if (isClientDisconnect(e)) {
			log.debug("Client disconnected before response was sent: {}", e.getMessage());
			return null;
		}

		e.printStackTrace();
		log.error("Exception - message:{}", e.getMessage(), e);

		// 현재 사용중이 profile정보를 확인하는 로직
		Environment environment = context.getEnvironment();
		String[] activeProfiles = environment.getActiveProfiles();
		String activeProfile = Arrays.stream(activeProfiles).findFirst().orElse("default");
//		IncomingWebhook.sendToJandi(e, activeProfile, logAspect.getRequestId());

		return this.resFail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	private boolean isClientDisconnect(Throwable t) {
		if (t instanceof ClientAbortException) {
			return true;
		}
		if (t instanceof IOException && "Broken pipe".equals(t.getMessage())) {
			return true;
		}
		Throwable cause = t.getCause();
		return cause != null && cause != t && isClientDisconnect(cause);
	}
}