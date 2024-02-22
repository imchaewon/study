package com.example.java_.floatingPoint.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Run {
	public static void main(String[] args) throws IOException {

		// 실수 10진수를 2진수로 변환
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String num = reader.readLine();

		String r_binary = toBinary(num);

		System.out.println(r_binary);

		double r_decimal = toDecimal(r_binary);

		System.out.println(r_decimal);

	}

	private static double toDecimal(String binary) {
		int point = binary.indexOf(".");

		String significand = binary.substring(0, point);
		String mantissa = binary.substring(point + 1);

		char[] arr_significand = significand.toCharArray();
		char[] arr_mantissa = mantissa.toCharArray();

		int r_significand = 0;
		double r_mantissa = 0;

		double result;

		for(int i=0;i<arr_significand.length;i++){
			if((arr_significand[i]+"").equals("1")) {
				r_significand += (int) Math.pow(2, arr_significand.length - i - 1);
			}
		}

		for(int i=0;i<arr_mantissa.length;i++){
			r_mantissa += (arr_mantissa[i]-'0') * (1 / Math.pow(2, i + 1));
		}

		result = (binary.charAt(0) == '-' ? -1 : 1) * (r_significand + r_mantissa);

		return result;
	}

	private static String toBinary(String num) {
		int point = num.indexOf(".");

		String significand = num.substring(0, point);
		String mantissa = num.substring(point + 1);

		boolean negative = num.charAt(0) == '-';
		if (negative)
			significand = significand.substring(1);

		int i = Integer.parseInt(significand);
		int j;
		int k = Integer.parseInt(mantissa);

		List<Integer> significandList = new ArrayList<>();
		List<Integer> mantissaList = new ArrayList<>();

		StringBuilder result = new StringBuilder();


		while (i>0){
			j = i % 2;
			i /= 2;
			if (i == 0) j = 1;
			significandList.add(j);
		}
		Collections.reverse(significandList);

		System.out.println(num);
		for (int l=0;l<10;l++){
			int len = (int)Math.log10(k)+1;
			k *= 2;
			boolean carry = (int)Math.log10(k) + 1 - len == 1;
			if (carry){
				mantissaList.add(1);
				k = Integer.parseInt((k+"").substring(1));
			}else{
				mantissaList.add(0);
			}
			if(k == 0)
				break;
		}

		if(negative)
			result.append("-");
		for(int item:significandList){
			result.append(item);
		}
		result.append(".");
		for(int item:mantissaList){
			result.append(item);
		}
		return String.valueOf(result);
	}
}
