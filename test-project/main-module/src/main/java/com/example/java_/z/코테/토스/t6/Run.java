package com.example.java_.z.코테.토스.t6;

//정확성 시간 제한 / 효율성 시간 제한 / 메모리 제한
//10초/ 언어별로 작성된 정답 코드의 실행 시간의 적정 배수/ 2GB

//문제 설명
//주식 쓸어담기
//김토스는 가지고 있는 돈을 긁어모아 주식을 구매하기로 했습니다. 자신만의 기준을 가지고 있는 투 자자로서, 김토스는 미리 구매할 만한 여러 주식들의 가격과 가치를 분석해 두었습니다. 그런데 문득 김토스는 자신이 이 주식들을 구매해서 얻을 수 있는 가치가 최대 얼마인지 알고 싶어졌습니다. 김토 스가 확보할 수 있는 가치의 최댓값을 구하세요.
//* 각각의 주식은 최대 한 개만 구매할 수 있습니다.

//구현사항

//입력
//money : 김토스가 가진 돈의 액수입니다.
//stocks[N][0] : 분석한 주식들의 가치입니다.
//stocks[N][1] : 분석한 주식들의 가격입니다.
//stocks[i][1]는 stocks[i][0]의 가격입니다. (1 ≤i< N)

//출력
//김토스가 확보할 수 있는 가치의 최댓값을 반환하세요.

//예시1
//// input
//money: 10
//stocks: [[1, 1], [3, 5], [3, 5], [4, 9]]
//// output
//정답: 6
//2번째, 3번째 주식을 구매하면 총 10의 금액으로 6의 가치를 얻을 수 있습니다.

//예시2
//// input
//money: 30
//stocks: [[1, 3111
//// output
//정답: 0
//구매할 수 있는 주식이 없으므로 얻을 수 있는 가치가 0입니다. 따라서 0을 반환합니다.
class Run {
	public static void main(String[] args) {
		int money1 = 10;
		long[][] stocks1 = {{1, 1}, {3, 5}, {3, 5}, {4, 9}};
		System.out.println(new Solution().solution(money1, stocks1));  // 출력: 6

		int money2 = 30;
		long[][] stocks2 = {{1, 3}, {2, 5}, {10, 10}};
		System.out.println(new Solution().solution(money2, stocks2));  // 출력: 12
	}
}

class Solution {
	public long solution(int money, long[][] stocks) {
		int n = stocks.length;
		long[] dp = new long[money + 1];

		for (int i = 0; i < n; i++) {
			for (int j = money; j >= stocks[i][1]; j--) {
				dp[j] = Math.max(dp[j], dp[j - (int) stocks[i][1]] + stocks[i][0]);
			}
		}

		return dp[money];
	}
}