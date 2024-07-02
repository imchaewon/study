package com.example.java_.enum_.t6;

import lombok.Getter;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		System.out.println(e1.USER_GRP);
		System.out.println(e1.USER_GRP.name());
		System.out.println(e1.USER_GRP.getCamel());
		System.out.println(Arrays.toString(e1.values()));
	}
}

@Getter
enum e1{
	USER_GRP("userGrp"),
	DEVICE_GRP("deviceGrp"),
	CLOUD_PLAT_TYPE("cloudPlatType"),
	ROLE("role"),
	DEVICE_TYPE("deviceType"),
	INTERFACE_TYPE("interfaceType");

	private final String camel;

	e1(String camel) {
		this.camel = camel;
	}
}