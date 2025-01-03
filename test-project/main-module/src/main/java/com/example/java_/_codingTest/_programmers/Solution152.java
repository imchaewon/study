package com.example.java_._codingTest._programmers;

import java.util.HashSet;
import java.util.Set;

//체육복
//점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
//학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
//체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
//전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
//체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
//전체 학생의 수는 2명 이상 30명 이하입니다.
//체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
//여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
//여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
//여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
public class Solution152 {
	public static void main(String[] args) {
		Solution152 s = new Solution152();
		System.out.println(s.solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
//		System.out.println(s.solution(5, new int[]{2, 4}, new int[]{3}));
//		System.out.println(s.solution(3, new int[]{3}, new int[]{1}));
	}

	public int solution(int n, int[] lost, int[] reserve) {
		int answer = n - lost.length; // 체육복을 잃어버린 학생 수를 뺀다

		// 여벌 체육복이 있는 학생이 도난당한 경우 처리
		Set<Integer> lostSet = new HashSet<>();
		Set<Integer> reserveSet = new HashSet<>();
		for (int i : lost) {
			lostSet.add(i);
		}
		for (int i : reserve) {
			if (lostSet.contains(i)) {
				lostSet.remove(i);
			} else {
				reserveSet.add(i);
			}
		}

		// 체육복 빌려주기
		for (int i : lostSet) {
			if (reserveSet.contains(i - 1)) { // 바로 앞 번호 학생이 여벌 체육복을 가지고 있는 경우
				answer++;
				reserveSet.remove(i - 1);
			} else if (reserveSet.contains(i + 1)) { // 바로 뒷 번호 학생이 여벌 체육복을 가지고 있는 경우
				answer++;
				reserveSet.remove(i + 1);
			}
		}

		return answer;
	}

}