package com.icw.stock.model.stock.req.overseas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.stock.model.stock.req.StockReqDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Builder
public class OverseasReqDTO implements StockReqDTO {
	private String date;
	private List<ExcdAndSymbDTO> excdAndSymbs;

	public static String convertToJson(List<ExcdAndSymbDTO> excdAndSymbs) {
		return convertToJson(excdAndSymbs, null);
	}

	public static String convertToJson(List<ExcdAndSymbDTO> excdAndSymbs, String date) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(of(excdAndSymbs, date));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String convertToJson(){
		return convertToJson(excdAndSymbs, date);
	}

	public static OverseasReqDTO of(List<ExcdAndSymbDTO> excdAndSymbs, String date) {
		OverseasReqDTOBuilder overseasReqDTOBuilder = OverseasReqDTO.builder()
				.excdAndSymbs(excdAndSymbs);
		if(date != null)
			overseasReqDTOBuilder.date(date);
		return overseasReqDTOBuilder.build();
	}

	public static OverseasReqDTO of(String reqTxt, String date) {
		log.debug("입력 데이터: {}", reqTxt);
		String[] lines = reqTxt.split("\n");
		List<ExcdAndSymbDTO> excdAndSymbs = new ArrayList<>();

		for (String line : lines) {
			if (line == null || line.trim().isEmpty()) {
				continue;
			}
			line = line.trim();
			log.debug("파싱 중인 줄: [{}]", line);
			
			// 탭으로 분리 시도
			String[] parts = line.split("\t");
			
			// 탭으로 분리되지 않으면 공백으로 분리 시도
			if (parts.length < 2) {
				parts = line.split("\\s+");
			}
			
			// 공백으로도 분리되지 않으면 쉼표로 분리 시도
			if (parts.length < 2) {
				parts = line.split(",");
			}
			
			// 여전히 분리되지 않으면 해당 줄은 건너뜀
			if (parts.length < 2) {
				log.warn("파싱 실패: 줄을 2개 이상의 부분으로 분리할 수 없습니다. [{}]", line);
				continue;
			}
			
			String excd = parts[0].trim();
			String symb = parts[1].trim();
			
			// 빈 문자열 체크
			if (excd.isEmpty() || symb.isEmpty()) {
				log.warn("빈 값 발견: excd=[{}], symb=[{}]", excd, symb);
				continue;
			}
			
			log.debug("파싱 성공: excd=[{}], symb=[{}]", excd, symb);
			excdAndSymbs.add(new ExcdAndSymbDTO(excd, symb));
		}

		log.info("총 {}개의 주식 코드 파싱 완료", excdAndSymbs.size());
		return OverseasReqDTO.builder()
				.excdAndSymbs(excdAndSymbs)
				.date(date)
				.build();
	}
}