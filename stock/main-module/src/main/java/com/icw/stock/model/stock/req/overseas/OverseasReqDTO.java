package com.icw.stock.model.stock.req.overseas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.stock.model.stock.req.StockReqDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
		String[] lines = reqTxt.split("\n");
		List<ExcdAndSymbDTO> excdAndSymbs = new ArrayList<>();

		for (String line : lines) {
			String[] parts = line.split("\t");
			String excd = parts[0];
			String symb = parts[1];
			excdAndSymbs.add(new ExcdAndSymbDTO(excd, symb));
		}

		return OverseasReqDTO.builder()
				.excdAndSymbs(excdAndSymbs)
				.date(date)
				.build();
	}
}