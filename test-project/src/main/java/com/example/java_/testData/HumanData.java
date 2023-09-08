package com.example.java_.testData;

import org.json.JSONObject;

public class HumanData {
	public static JSONObject getData(){

		String jsonData = "{data:[" +
				"{" +
				"\"name\":\"김준수\"," +
				"\"gender\":\"m\"," +
				"\"birth\":\"19980624\"," +
				"\"height\":\"175\"," +
				"\"weight\":\"80.0\"," +
				"\"address\":\"성남시 분당구 판교동\"" +
				"}," +
				"{" +
				"\"name\":\"한가인\"," +
				"\"gender\":\"f\"," +
				"\"birth\":\"19820202\"," +
				"\"height\":\"167\"," +
				"\"weight\":\"48.1\"," +
				"\"address\":\"인천광역시 동구 송림동 42-215\"" +
				"}," +
				"{" +
				"\"name\":\"이열음\"," +
				"\"gender\":\"f\"," +
				"\"birth\":\"19960216\"," +
				"\"height\":\"165\"," +
				"\"weight\":\"45.2\"," +
				"\"address\":\"서울특별시 중구 장충동1가 54-1\"" +
				"}" +
				"]}";

		return new JSONObject(jsonData);
	}
}
