package com.example.java_._codingTest.otherTest.토스.t1;

//정확성 시간 제한 / 메모리 제한
//10초 / 2GB

//문제 설명
//적당히 어려운 문제
//당신은 스타트업의 멤버로서 새로운 개발자들을 채용하기 위해, 코딩테스트 문제를 선정하는 역할을 맡았습니다.
//문제를 선정한다는 것은 다른 팀원들이 만든 문제 중 '적당히 어려운 문제'를 골라낸다는 뜻입니다.
//여기서 적당히 어려운 문제란 '상위 25% 이내의 난이도를 가진 문제 중 가장 쉬운 문제'를 말합니다.
//각 문제의 난이도는 이미 정해져 있으며, 정수로 표기합니다.

//구현사항

//입력
//Levels[N] : 문제들의 난이도를 나타냅니다.
//1 ≤ N ≤ 10000
//1 ≤ levels[i] ≤ 2147483647 (0 ≤ i < N)
//levels[i] * levels[j] (0 ≤ i, j < N, i != j)

//출력
//조건에 맞는 문제의 난이도를 반환하세요. 만약 조건에 해당하는 문제가 없다면 -1을 반환하세요.

//예시 문제

//예시1
// input
//levels: [1,2,3,4]

// output
//정답: 4
//제시된 levels 에서 상위 25%의 문제는 난이도 4의 문제 하나뿐입니다.

//예시2

// input
//levels: [1,2,3,4,5,6,7,8,9]

import java.util.Arrays;

// output
//정답: 8
//위 Levels 에서 상위 25%의 문제는 난이도 8과 9에 해당하는 두 개 있습니다. (만약 세 개를 선택 한다면 총 비중이 약 33.3%이 되어 조건을 충족하지 못하게 됩니다.) 조건에 맞는 두 문제 중 더 쉬 운 문제는 8입니다.
public class Run {
	public static void main(String[] args) {
		System.out.println(new Solution().solution(new int[]{1, 2, 3, 4}));
		System.out.println(new Solution().solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
	}
}

class Solution {
	public int solution(int[] levels) {
		// 난이도를 기준으로 오름차순 정렬
		Arrays.sort(levels);

		// 상위 25%에 해당하는 문제의 개수를 계산
		int n = levels.length;
		int top25Percent = (int)(n * .25);

		// 상위 25%의 문제 중 가장 쉬운 문제의 등수를 반환
		if (top25Percent == 0) {
			return -1;
		} else {
			return levels[n - top25Percent];
		}
	}
}