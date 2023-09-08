package com.example.java_.z.프로그래머스문제;

//소수 만들기
//주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다.
//숫자들이 들어있는 배열 nums가 매개변수로 주어질 때, nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
//nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
//nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
public class Solution145 {
	public static void main(String[] args) {
		Solution145 s = new Solution145();
		System.out.println(s.solution(new int[]{1,2,3,4}));
		System.out.println(s.solution(new int[]{1,2,7,6,4}));
	}

	public int solution(int[] nums) {
		int answer = 0;
		for (int i = 0; i < nums.length; i++)
			for (int i1 = i + 1; i1 < nums.length; i1++)
				for (int i2 = i1 + 1; i2 < nums.length; i2++)
					if(isPrime(nums[i] + nums[i1] + nums[i2]))
						answer++;
		return answer;
	}

	public static boolean isPrime(int num){
		for(int i = 2; i * i <= num; i++)
			if(num % i == 0) return false;
		return true;
	}
}



