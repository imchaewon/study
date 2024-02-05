package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//분수의 덧셈
//첫 번째 분수의 분자와 분모를 뜻하는 denum1, num1, 두 번째 분수의 분자와 분모를 뜻하는 denum2, num2가 매개변수로 주어집니다.
//두 분수를 더한 값을 기약 분수로 나타냈을 때 분자와 분모를 순서대로 담은 배열을 return 하도록 solution 함수를 완성해보세요.
public class Solution100 {
	public static void main(String[] args) {
		Solution100 s = new Solution100();
		System.out.println(Arrays.toString(s.solution(1, 2, 3, 4)));
		System.out.println(Arrays.toString(s.solution(9, 2, 1, 3)));
	}

	public int[] solution(int denum1, int num1, int denum2, int num2) {
		int lcm = num1 * num2 / GCD(num1, num2);
		int d = denum1 * (lcm / num1) + denum2 * (lcm / num2);
		int lcm2 = GCD(lcm, d);
		return new int[]{d / lcm2, lcm / lcm2};
	}

	int GCD(int a, int b){
		return a % b == 0 ? b : GCD(b, a % b);
	}

}










