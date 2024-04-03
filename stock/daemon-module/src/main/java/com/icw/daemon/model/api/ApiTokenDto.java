package com.icw.daemon.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApiTokenDto {
	@JsonProperty("access_token")
	String accessToken;
	@JsonProperty("token_type")
	String tokenType;
	@JsonProperty("expires_in")
	Integer expiresIn;
	@JsonProperty("access_token_token_expired")
	String accessTokenExpired;
}
