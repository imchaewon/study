package com.example.java_._codingTest.opensearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class FilebeatLogMessageDto {
	@JsonProperty("message")
	private String message;

	@JsonProperty("time")
	private long time;

	@JsonProperty("time_text")
	private String timeText;

	@JsonProperty("term")
	private String term;

	@JsonProperty("log_path")
	private String logPath;

	public void setTimeText(String timeText) {
		this.timeText = timeText;

		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		try {
			Date date = isoFormat.parse(this.timeText);
			this.time = date.getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}