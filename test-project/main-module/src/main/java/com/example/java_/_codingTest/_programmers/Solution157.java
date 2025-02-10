package com.example.java_._codingTest._programmers;

//명예의 전당 (1)
//문제 설명
//"명예의 전당"이라는 TV 프로그램에서는 매일 1명의 가수가 노래를 부르고, 시청자들의 문자 투표수로 가수에게 점수를 부여합니다.
// 매일 출연한 가수의 점수가 지금까지 출연 가수들의 점수 중 상위 k번째 이내이면 해당 가수의 점수를 명예의 전당이라는 목록에 올려 기념합니다.
// 즉 프로그램 시작 이후 초기에 k일까지는 모든 출연 가수의 점수가 명예의 전당에 오르게 됩니다.
// k일 다음부터는 출연 가수의 점수가 기존의 명예의 전당 목록의 k번째 순위의 가수 점수보다 더 높으면,
// 출연 가수의 점수가 명예의 전당에 오르게 되고 기존의 k번째 순위의 점수는 명예의 전당에서 내려오게 됩니다.
//
//이 프로그램에서는 매일 "명예의 전당"의 최하위 점수를 발표합니다. 예를 들어, k = 3이고,
// 7일 동안 진행된 가수의 점수가 [10, 100, 20, 150, 1, 100, 200]이라면,
// 명예의 전당에서 발표된 점수는 아래의 그림과 같이 [10, 10, 10, 20, 20, 100, 100]입니다.

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution157 {
	public static void main(String[] args) {

		Solution157 s = new Solution157();
		System.out.println(Arrays.toString(s.solution(
				3, new int[]{10, 100, 20, 150, 1, 100, 200} // [10,10,10,20,20,100,100]
		)));
		System.out.println(Arrays.toString(s.solution(
				4, new int[]{0, 300, 40, 300, 20, 70, 150, 50, 500, 1000} // [0,0,0,0,20,40,70,70,150,300]
		)));
		System.out.println(Arrays.toString(s.solution(
				9, new int[]{10, 30, 40, 3, 0, 20, 4} // [10, 10, 10, 3, 0, 0, 0]
		)));

	}

	public int[] solution(int k, int[] scores) {
//		List<Integer> chart = new ArrayList<>();
//		List<Integer> lows = new ArrayList<>();
//
//		for (int i = 0; i < scores.length; i++) {
//			int score = scores[i];
//			if(i == 0) {
//				chart.add(score);
//				lows.add(score);
//				continue;
//			}
//
//			List<Integer> topNTmp;
//			topNTmp = new ArrayList<>(chart);
//			for (int c : chart) {
//				if(topNTmp.size() < k){ // 차트최대개수보다 현재개수가 적으면
//					topNTmp.add(score);
//					break;
//				}else if(score > c){ // 같거나 큰 경우 & 현재 점수가 차트최하위보다 크면
//					topNTmp.remove((Integer) c);
//					topNTmp.add(score);
//					break;
//				}
//			}
//			topNTmp.sort(Integer::compare);
//			lows.add(topNTmp.get(0));
//			chart = new ArrayList<>(topNTmp);
//		}
//
//		return lows.stream().mapToInt(Integer::intValue).toArray();

		int[] answer = new int[scores.length];

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

		int temp = 0;

		for(int i = 0; i < scores.length; i++) {

			priorityQueue.add(scores[i]);
			if (priorityQueue.size() > k) {
				priorityQueue.poll();
			}

			answer[i] = priorityQueue.peek();
		}



		return answer;
	}
}