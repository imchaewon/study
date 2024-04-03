package com.icw.stock.model.stock.req.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ApiTokenReqDTO {
	@JsonProperty("grant_type")
	private final String grant_type = "client_credentials";
	@JsonProperty("appkey")
	private String appkey;
	@JsonProperty("appsecret")
	private String appsecret;
}
