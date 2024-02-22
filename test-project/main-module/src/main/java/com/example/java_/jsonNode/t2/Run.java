package com.example.java_.jsonNode.t2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {

		String json = "{\"body\": {" +
				"    \"items\": [" +
				"        {" +
				"            \"item\": {" +
				"                \"contentid\": \"125534\"," +
				"                \"petTursmInfo\": \"\"," +
				"                \"relaAcdntRiskMtr\": \"입장 전 동의서 작성\"," +
				"                \"acmpyTypeCd\": \"동반가능\"," +
				"                \"relaPosesFclty\": \"\"," +
				"                \"relaFrnshPrdlst\": \"\"," +
				"                \"etcAcmpyInfo\": \"- 목줄 착용 및 이동장 이용시 동반입장 가능," +
				"    - 맹견은 입마개 필수" +
				"    - 입장 전 매표소에서 동의서 작성\"," +
				"                \"relaPurcPrdlst\": \"\"," +
				"                \"acmpyPsblCpam\": \"제한 없음\"," +
				"                \"relaRntlPrdlst\": \"\"," +
				"                \"acmpyNeedMtr\": \"입마개 착용,목줄 착용\"" +
				"            }" +
				"        }" +
				"    ]," +
				"    \"numOfRows\": 12," +
				"    \"pageNo\": 123," +
				"    \"totalCount\": 1234" +
				"}}";

		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(json);
		Map<String, Object> map = mapper.readValue(json, new TypeReference<>(){});
		System.out.println(obj);
		System.out.println(map);

		System.out.println();

		System.out.println(map.get("body"));
		System.out.println(((HashMap<String, Object>)map.get("body")).get("pageNo"));
		System.out.println(((HashMap<String, Object>)map.get("body")).get("items"));
		System.out.println(((ArrayList<HashMap<String, Object>>)((HashMap<String, Object>)map.get("body")).get("items")).get(0));
		System.out.println(((ArrayList<HashMap<String, Object>>)((HashMap<String, Object>)map.get("body")).get("items")).get(0).get("item"));

	}
}
