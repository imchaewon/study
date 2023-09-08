package com.example.java_.gofDesignPatterns.structural.facade.t1;

public class Client {
	public static void main(String[] args) {
		FacadeService facadeService = new FacadeService();
		facadeService.operate("Client");
	}
}
