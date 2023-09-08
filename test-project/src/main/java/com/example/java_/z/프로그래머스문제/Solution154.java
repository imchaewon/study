package com.example.java_.z.프로그래머스문제;

//구명보트
//문제 설명
//무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.
//
//예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.
//
//구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
//
//사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.

//제한사항
//무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
//각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
//구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
//구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.

//입출력 예
//people	limit	return
//[70, 50, 80, 50]	100	3
//[70, 80, 50]	100	3

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution154 {
	public static void main(String[] args) {

		Solution154 s = new Solution154();

//		System.out.println(s.solution(new int[]{70, 50, 80, 50}, 100));
//		System.out.println(s.solution(new int[]{10, 20, 20, 20, 20}, 100));
//		System.out.println(s.solution(new int[]{70, 80, 50}, 100));
		System.out.println(s.solution(new int[]{40, 50, 150, 160}, 200)); // 2
//		System.out.println(s.solution(new int[]{100, 500, 500, 900, 950}, 1000)); // 3

	}

	public int solution(int[] people, int limit) {
		int calc = limit;
		int sum = 0;
		for (int i = 0; i < people.length - 1; i++) {
			System.out.println(i);
			System.out.println(people[i]);
			sum += people[i];
			if(sum > limit) {
				System.out.println(11111);
				System.out.println(sum);
				break;
			}else {
				System.out.println(22222);
				calc = Math.min(calc, limit - sum);
			}
		}
		System.out.println("calc: " + calc);
		return 0;
	}
}


















