package com.example.java_._codingTest._programmers;

//[PCCE 기출문제] 9번 / 지폐 접기
//민수는 다양한 지폐를 수집하는 취미를 가지고 있습니다. 지폐마다 크기가 달라 지갑에 넣으려면 여러 번 접어서 넣어야 합니다.
//예를 들어 지갑의 크기가 30 * 15이고 지폐의 크기가 26 * 17이라면 한번 반으로 접어 13 * 17 크기로 만든 뒤 90도 돌려서 지갑에 넣을 수 있습니다.
//
//지폐를 접을 때는 다음과 같은 규칙을 지킵니다.
//지폐를 접을 때는 항상 길이가 긴 쪽을 반으로 접습니다.
//접기 전 길이가 홀수였다면 접은 후 소수점 이하는 버립니다.
//접힌 지폐를 그대로 또는 90도 돌려서 지갑에 넣을 수 있다면 그만 접습니다.
//지갑의 가로, 세로 크기를 담은 정수 리스트 wallet과 지폐의 가로, 세로 크기를 담은 정수 리스트 bill가 주어질 때, 지갑에 넣기 위해서 지폐를 최소 몇 번 접어야 하는지 return하도록 solution함수를 완성해 주세요.

public class Solution167 {
	public static void main(String[] args) {
		Solution167 s = new Solution167();
		System.out.println(s.solution(new int[]{30, 15}, new int[]{26, 17})); // 1
		System.out.println(s.solution(new int[]{50, 50}, new int[]{100, 241})); // 4
		System.out.println(s.solution(new int[]{250, 120}, new int[]{100, 240})); // 0
	}

	public int solution(int[] wallet, int[] bill) {
		int walletWidth = wallet[0];
		int walletHeight = wallet[1];
		int billWidth = bill[0];
		int billHeight = bill[1];
		int result = 0;
		while (true) {
			if(walletWidth >= billWidth && walletHeight >= billHeight){
				return result;
			} else if(walletWidth >= billHeight && walletHeight >= billWidth){
				return result;
			} else {
				if (billWidth > billHeight) {
					billWidth /= 2;
				}else{
					billHeight /= 2;
				}
				result++;
			}
		}
	}
}