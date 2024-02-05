package com.example.java_.z.프로그래머스;

//치킨 쿠폰
//프로그래머스 치킨은 치킨을 시켜먹으면 한 마리당 쿠폰을 한 장 발급합니다.
//쿠폰을 열 장 모으면 치킨을 한 마리 서비스로 받을 수 있고, 서비스 치킨에도 쿠폰이 발급됩니다.
//시켜먹은 치킨의 수 chicken이 매개변수로 주어질 때 받을 수 있는 최대 서비스 치킨의 수를 return하도록 solution 함수를 완성해주세요.
public class Solution91 {
	public static void main(String[] args) {
		Solution91 s = new Solution91();
//		System.out.println(s.solution(100));
		System.out.println(s.solution(1081));
	}

	public int solution(int chicken) {
		int service = 0;
		int coupon = chicken;
		while(coupon >= 10){
			int tmp = coupon % 10;
			service += coupon / 10;
			coupon /= 10;
			coupon += tmp;
		}
		return service;
	}
}










