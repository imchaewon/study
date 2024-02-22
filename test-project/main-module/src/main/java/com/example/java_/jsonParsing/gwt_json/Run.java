package com.example.java_.jsonParsing.gwt_json;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

import gwt.material.design.jscore.client.api.JSON;
import gwt.material.design.jscore.client.api.JsObject;

// gwt는 여기서 테스트 불가 (pom.xml 의존성 추가해도 안된다고..)
public class Run {
	public static void main(String[] args) {

		Object jsonStr = "[\"result\":{\"COMP_ORDER\":0, \"CREATE_DATE\":\"2022-04-06 12:42:00.0\", \"ODM_ID\":\"7870c5ee-0261-4df9-b5c3-a74a496a8913\", \"VIEW_TITLE\":0, \"OTD_ID\":\"46a412aa-0b3b-11ea-869b-020027310001\", \"IMG_ID\":\"dd69a168-5b89-4de1-a3a6-6eb21bf5bc36\", \"COMP_ID\":\"01dd8d61-78f4-42ea-a013-bd9572a82baa\", \"CONTENT_TITLE\":\"\", \"COT_ORDER\":0, \"TITLE\":\"여행재생목록\", \"TEMPLATE_ID\":\"6\", \"MAIN_AREA\":\"A\"},{\"COMP_ORDER\":0, \"VIEW_TITLE\":1, \"IMG_ID\":\"26b2fc07-a882-4054-a81c-8b90e04eb581\", \"CONTENT_TITLE\":\"여행재생목록\", \"AREA_CODE\":-1, \"LINK_URL\":\"https://dev.ktovisitkorea.com/detail/rem_detail.do?cotid=8220ece6-3ec4-43fd-80f1-e85539dce412&temp=\", \"TEMPLATE_ID\":\"6\", \"MAIN_AREA\":\"A\", \"CREATE_DATE\":\"2022-04-06 12:42:53.0\", \"ODM_ID\":\"2c110e65-29b0-4887-991c-fa1199e56972\", \"OTD_ID\":\"46a412aa-0b3b-11ea-869b-020027310001\", \"SIGUNGU_CODE\":-1, \"IMAGE_DESCRIPTION\":\"\", \"COMP_ID\":\"01dd8d61-78f4-42ea-a013-bd9572a82baa\", \"COT_ORDER\":1},{\"COMP_ORDER\":1, \"CREATE_DATE\":\"2022-04-06 12:43:05.0\", \"ODM_ID\":\"0bbcd90d-96a3-41c1-a7c6-6228bf66a653\", \"VIEW_TITLE\":0, \"OTD_ID\":\"46a412aa-0b3b-11ea-869b-020027310001\", \"IMG_ID\":\"666db75b-ba22-4c31-a910-987fece0ba6c\", \"COMP_ID\":\"5ca3f7c0-c3b2-4fe7-8e9f-00a701f42255\", \"CONTENT_TITLE\":\"\", \"COT_ORDER\":0, \"TITLE\":\"지역여행 프로그램\", \"TEMPLATE_ID\":\"6\", \"MAIN_AREA\":\"A\"},{\"COMP_ORDER\":1, \"VIEW_TITLE\":1, \"IMG_ID\":\"6e748e8c-8f36-4629-bf9d-1a9da84436e0\", \"CONTENT_TITLE\":\"지역여행 프로그램\", \"AREA_CODE\":-1, \"LINK_URL\":\"https://dev.ktovisitkorea.com/other/2021_other_area.do?otdid=46a412aa-0b3b-11ea-869b-020027310001\", \"TEMPLATE_ID\":\"6\", \"MAIN_AREA\":\"A\", \"CREATE_DATE\":\"2022-04-06 12:45:14.0\", \"ODM_ID\":\"700ad6c2-a17c-4dd5-969f-577153966f5a\", \"OTD_ID\":\"46a412aa-0b3b-11ea-869b-020027310001\", \"SIGUNGU_CODE\":-1, \"IMAGE_DESCRIPTION\":\"\", \"COMP_ID\":\"5ca3f7c0-c3b2-4fe7-8e9f-00a701f42255\", \"COT_ORDER\":1},{\"COMP_ORDER\":2, \"CREATE_DATE\":\"2022-04-06 12:45:22.0\", \"ODM_ID\":\"60ac0a92-d234-4fd9-a377-3d3e10b4801c\", \"VIEW_TITLE\":0, \"OTD_ID\":\"46a412aa-0b3b-11ea-869b-020027310001\", \"IMG_ID\":\"d5d4139d-2283-414c-93ee-5196e8fcc72a\", \"COMP_ID\":\"dc2da22c-0f02-45cc-ade8-c81771fcb960\", \"CONTENT_TITLE\":\"\", \"COT_ORDER\":0, \"TITLE\":\"여행업계 특별관\", \"TEMPLATE_ID\":\"6\", \"MAIN_AREA\":\"A\"}]";

		JSONObject jsonObj = (JSONObject) JSONParser.parseStrict(JSON.stringify((JsObject)jsonStr));

		System.out.println(jsonObj.get("result").isArray().get(0).isObject().containsKey("COMP_ORDER"));









		//{"result":[{"COMP_ORDER":0, "CREATE_DATE":"2022-04-06 12:42:00.0", "ODM_ID":"7870c5ee-0261-4df9-b5c3-a74a496a8913", "VIEW_TITLE":0, "OTD_ID":"46a412aa-0b3b-11ea-869b-020027310001", "IMG_ID":"dd69a168-5b89-4de1-a3a6-6eb21bf5bc36", "COMP_ID":"01dd8d61-78f4-42ea-a013-bd9572a82baa", "CONTENT_TITLE":"", "COT_ORDER":0, "TITLE":"여행재생목록", "TEMPLATE_ID":"6", "MAIN_AREA":"A"},{"COMP_ORDER":0, "VIEW_TITLE":1, "IMG_ID":"26b2fc07-a882-4054-a81c-8b90e04eb581", "CONTENT_TITLE":"여행재생목록", "AREA_CODE":-1, "LINK_URL":"https://dev.ktovisitkorea.com/detail/rem_detail.do?cotid=8220ece6-3ec4-43fd-80f1-e85539dce412&temp=", "TEMPLATE_ID":"6", "MAIN_AREA":"A", "CREATE_DATE":"2022-04-06 12:42:53.0", "ODM_ID":"2c110e65-29b0-4887-991c-fa1199e56972", "OTD_ID":"46a412aa-0b3b-11ea-869b-020027310001", "SIGUNGU_CODE":-1, "IMAGE_DESCRIPTION":"", "COMP_ID":"01dd8d61-78f4-42ea-a013-bd9572a82baa", "COT_ORDER":1},{"COMP_ORDER":1, "CREATE_DATE":"2022-04-06 12:43:05.0", "ODM_ID":"0bbcd90d-96a3-41c1-a7c6-6228bf66a653", "VIEW_TITLE":0, "OTD_ID":"46a412aa-0b3b-11ea-869b-020027310001", "IMG_ID":"666db75b-ba22-4c31-a910-987fece0ba6c", "COMP_ID":"5ca3f7c0-c3b2-4fe7-8e9f-00a701f42255", "CONTENT_TITLE":"", "COT_ORDER":0, "TITLE":"지역여행 프로그램", "TEMPLATE_ID":"6", "MAIN_AREA":"A"},{"COMP_ORDER":1, "VIEW_TITLE":1, "IMG_ID":"6e748e8c-8f36-4629-bf9d-1a9da84436e0", "CONTENT_TITLE":"지역여행 프로그램", "AREA_CODE":-1, "LINK_URL":"https://dev.ktovisitkorea.com/other/2021_other_area.do?otdid=46a412aa-0b3b-11ea-869b-020027310001", "TEMPLATE_ID":"6", "MAIN_AREA":"A", "CREATE_DATE":"2022-04-06 12:45:14.0", "ODM_ID":"700ad6c2-a17c-4dd5-969f-577153966f5a", "OTD_ID":"46a412aa-0b3b-11ea-869b-020027310001", "SIGUNGU_CODE":-1, "IMAGE_DESCRIPTION":"", "COMP_ID":"5ca3f7c0-c3b2-4fe7-8e9f-00a701f42255", "COT_ORDER":1},{"COMP_ORDER":2, "CREATE_DATE":"2022-04-06 12:45:22.0", "ODM_ID":"60ac0a92-d234-4fd9-a377-3d3e10b4801c", "VIEW_TITLE":0, "OTD_ID":"46a412aa-0b3b-11ea-869b-020027310001", "IMG_ID":"d5d4139d-2283-414c-93ee-5196e8fcc72a", "COMP_ID":"dc2da22c-0f02-45cc-ade8-c81771fcb960", "CONTENT_TITLE":"", "COT_ORDER":0, "TITLE":"여행업계 특별관", "TEMPLATE_ID":"6", "MAIN_AREA":"A"}]}"

	}
}
