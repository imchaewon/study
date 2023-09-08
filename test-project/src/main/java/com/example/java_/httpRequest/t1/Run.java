package com.example.java_.httpRequest.t1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Run {
	public static void main(String[] args) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=elZJClL%2BcPyzL%2FwCk5Z3AtxWcAm6m%2FHITT9Uo8kvMuX6OEvsLeT9qBgSYFR1wpSVWM%2FQrzoItQZlAWU%2Fh1x5LA%3D%3D"); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* XML/JSON 여부 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = null;
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));
		else
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null)
			sb.append(line);
		rd.close();
		conn.disconnect();

		System.out.println(sb);

	}
}