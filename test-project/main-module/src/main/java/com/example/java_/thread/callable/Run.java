package com.example.java_.thread.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Run {

	public static void main(String[] args) throws Exception {

		Member m1 = new Member(1,"a");
		Member m2 = new Member(2,"b");

		System.out.println(createMemberList(m1,m2).call());
	}

	private static Callable<List<Member>> createMemberList(Member... members) {
		return () -> {
			List<Member> resultList = Arrays.asList(members);
			return resultList;
		};
	}
}
