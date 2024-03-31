package com.icw.stock.common;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

@Slf4j
@Builder
public class IncomingWebhook {

	private static final String WEB_HOOK_URL = "https://wh.jandi.com/connect-api/webhook/20317820/7f56e379d44e94d1f9158ef7fc845fcb";

	public static void sendToJandi(Exception e, String activeProfile, String requestId) {
		String httpBody = makeExceptionMsg(e, activeProfile, requestId);
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create(WEB_HOOK_URL))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(httpBody))
				.build();

		httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept(System.out::println)
				.exceptionally(error -> {
					log.error("Error occurred", error);
					return null;
				})
				.join();
	}

	private static String makeExceptionMsg(Exception e, String activeProfile, String requestId) {
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException ex) {
			log.error("Server IP acquisition failed", ex);
			return null;
		}

		String exceptionType = e.getClass().getSimpleName();
		String stackTrace = Arrays.toString(e.getStackTrace());

		return "{\n" +
				"\"body\" : \"[Exception]\",\n" +
				"\"connectColor\" : \"#FAC11B\",\n" +
				"\"connectInfo\" : [\n" +
				"{\n" +
				"\"title\" : \"Server IP\",\n" +
				"\"description\" : \"" + (ip != null ? ip.getHostAddress() : null) + "\"\n" +
				"}\n" +
				"," +
				"{\n" +
				"\"title\" : \"Current-Profile\",\n" +
				"\"description\" : \"" + activeProfile + "\"\n" +
				"}\n" +
				"," +
				"{\n" +
				"\"title\" : \"Request-UUID\",\n" +
				"\"description\" : \"" + requestId + "\"\n" +
				"}\n" +
				"," +
				"{\n" +
				"\"title\" :  \"" + exceptionType + "\",\n" +
				"\"description\" : \"" + stackTrace + "\"\n" +
				"}\n" +
				"]\n" +
				"}";
	}
}
