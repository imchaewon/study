package com.icw.stock.model.stock.req.domestic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.stock.model.stock.req.StockReqDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
public class DomesticReqDTO implements StockReqDTO {
	private String date;
	private List<String> ids;

	public static String convertToJson(List<String> ids) {
		return convertToJson(ids, null);
	}

	public static String convertToJson(List<String> ids, String date) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(of(ids, date));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String convertToJson(){
		return convertToJson(ids, date);
	}

	public static DomesticReqDTO of(List<String> ids, String date) {
		DomesticReqDTO.DomesticReqDTOBuilder domesticReqDTOBuilder = DomesticReqDTO.builder()
				.ids(ids);
		if(date != null)
			domesticReqDTOBuilder.date(date);
		return domesticReqDTOBuilder.build();
	}

	public static DomesticReqDTO of(String reqTxt, String date) {
		String[] lines = reqTxt.split("\n");
		List<String> ids = new ArrayList<>(Arrays.asList(lines));

		return DomesticReqDTO.builder()
				.ids(ids)
				.date(date)
				.build();
	}
}