//package com.example.test.z.코딩테스트1;
//
//public class Run {
//	public static void main(String[] args) {
//
//		int servers = 2;
//		boolean sticjy = true;
//		int[] request = new int[]{1,1,2,2};
//
//		Solution s = new Solution();
//		int[][] result = s.solution(servers, sticjy, request);
//
//		System.out.println(result);
//
//	}
//
//
//	static class Solution {
//		public int[][] solution(int servers, boolean sticky, int[] requests) {
//			int[][] answer = {};
//
//			for (int i = 0; i < servers; i++) {
//				answer[(i+1) % servers][] = requests[i];
//
//			}
//
//			return answer;
//		}
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
