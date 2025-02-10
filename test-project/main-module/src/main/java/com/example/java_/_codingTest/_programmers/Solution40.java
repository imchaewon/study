package com.example.java_._codingTest._programmers;

//옷가게 할인 받기
//머쓱이네 옷가게는 10만 원 이상 사면 5%, 30만 원 이상 사면 10%, 50만 원 이상 사면 20%를 할인해줍니다.
//구매한 옷의 가격 price가 주어질 때, 지불해야 할 금액을 return 하도록 solution 함수를 완성해보세요.
public class Solution40 {
	public static void main(String[] args) {
		Solution40 s = new Solution40();
		System.out.println(s.solution(150000));
	}

	public int solution(int price) {
		double dc = 0;
		if(price >= 500000)
			dc = .2;
		else if(price >= 300000)
			dc = .1;
		else if(price >= 100000)
			dc = .05;
		return (int) (price - price * dc);
	}

}