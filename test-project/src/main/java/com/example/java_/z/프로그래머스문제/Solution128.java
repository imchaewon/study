package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//최대공약수와 최소공배수
//두 수를 입력받아 두 수의 최대공약수와 최소공배수를 반환하는 함수, solution을 완성해 보세요.
//배열의 맨 앞에 최대공약수, 그다음 최소공배수를 넣어 반환하면 됩니다.
//예를 들어 두 수 3, 12의 최대공약수는 3, 최소공배수는 12이므로 solution(3, 12)는 [3, 12]를 반환해야 합니다.
public class Solution128 {
	public static void main(String[] args) {
		Solution128 s = new Solution128();
		System.out.println(Arrays.toString(s.solution(3, 12)));
	}

	public int[] solution(int n, int m) {
		return new int[]{gcd(n, m), n*m / gcd(n,m)};
	}

	int gcd(int a, int b){
		return a % b == 0 ? b : gcd(b, a % b);
	}
}



