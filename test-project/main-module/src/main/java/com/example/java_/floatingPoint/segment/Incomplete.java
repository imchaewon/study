package com.example.java_.floatingPoint.segment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Incomplete {
	public static void main(String[] args) throws IOException {

		// 실수 10진수를 2진수로 변환
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String num = reader.readLine();

		int bit = 32; // float=32bit / double=64bit

		int[] result = toBinary(num, bit);

		for (int i = 1; i <= bit; i++) {
			System.out.print(result[i-1]);
			if(i % 10 == 0) {
				System.out.println();
			}
		}

	}

	private static int[] toBinary(String num, int bit) {

		if(!(bit == 32 || bit == 64)){
			new Exception("없는bit");
			return null;
		}

		int point = num.indexOf(".");

		String significand = num.substring(0, point);
		String mantissa = num.substring(point + 1);

		boolean negative = num.charAt(0) == '-';
		if (negative)
			significand = significand.substring(1);

		int i = Integer.parseInt(significand);
		int j;
		int k = Integer.parseInt(mantissa);

		String indices;

		int[] result = new int[bit];

		// 부호비트 1자리
		result[0] = negative ? 1 : 0;


		List<Integer> significandList = new ArrayList<>();
		while (i>0){
			j = i % 2;
			i /= 2;
			if (i == 0) j = 1;
			significandList.add(j);
		}
		Collections.reverse(significandList);

		List<Integer> mantissaList = new ArrayList<>();
		for (int l=0;l<100;l++){
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

//		System.out.println(significandList);
//		System.out.println(mantissaList);
		List<Integer> mixList = new ArrayList<>();
		mixList.addAll(significandList);
		mixList.addAll(mantissaList);
//		System.out.println(mixList);

		indices = Integer.toBinaryString(significandList.size() - 1 + (bit==32?127:1023));

		// 지수부 float: 2~9자리 (8), double: 2~12자리 (11)
		for (int l = 1; l < (bit==32?9:11); l++)
			result[l] = Integer.parseInt(indices.charAt(l-1)+"");

		// float: 가수부 float: 10~32자리 (23), double: 13~64자리 (52)
		for (int l = 0; l < mixList.size() && l < (bit==32?23:52); l++)
			result[l + (bit==32?9:12)] = mixList.get(l);

		return result;
	}
}
