package com.example.java_;

import org.json.JSONObject;

public class SampleJSONDataInclusiveNull {
	public static JSONObject getData(){
		String s1 = "{\"data\":[\n" +
				"{\n" +
				"\"no\":\"001\",\n" +
				"\"name\":\"아이유\",\n" +
				"\"height\":\"162\",\n" +
				"\"address\":\"성남시 분당구 판교동\"\n" +
				"},\n" +
				"{\n" +
				"\"no\":\"002\",\n" +
				"\"name\":\"한가인\",\n" +
				"\"gender\":\"f\",\n" +
				"\"birth\":\"820202\",\n" +
				"\"weight\":\"48.1\",\n" +
				"\"address\":\"인천광역시 동구 송림동 42-215\"\n" +
				"},\n" +
				"{\n" +
				"\"no\":\"003\",\n" +
				"\"name\":\"이열음\",\n" +
				"\"gender\":\"f\",\n" +
				"\"height\":\"165\",\n" +
				"\"weight\":\"45.2\",\n" +
				"\"address\":\"서울특별시 중구 장충동1가 54-1\"\n" +
				"}\n" +
				"]}";
		return new JSONObject(s1);
	}
}
