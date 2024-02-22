package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//캐릭터의 좌표
//머쓱이는 RPG게임을 하고 있습니다.
//게임에는 up, down, left, right 방향키가 있으며 각 키를 누르면 위, 아래, 왼쪽, 오른쪽으로 한 칸씩 이동합니다.
//예를 들어 [0,0]에서 up을 누른다면 캐릭터의 좌표는 [0, 1], down을 누른다면 [0, -1], left를 누른다면 [-1, 0], right를 누른다면 [1, 0]입니다.
//머쓱이가 입력한 방향키의 배열 keyinput와 맵의 크기 board이 매개변수로 주어집니다.
//캐릭터는 항상 [0,0]에서 시작할 때 키 입력이 모두 끝난 뒤에 캐릭터의 좌표 [x, y]를 return하도록 solution 함수를 완성해주세요.
//[0, 0]은 board의 정 중앙에 위치합니다. 예를 들어 board의 가로 크기가 9라면 캐릭터는 왼쪽으로 최대 [-4, 0]까지 오른쪽으로 최대 [4, 0]까지 이동할 수 있습니다.
public class Solution85 {
	public static void main(String[] args) {
		Solution85 s = new Solution85();
//		System.out.println(Arrays.toString(s.solution(new String[]{"left", "right", "up", "right", "right"}, new int[]{11, 11})));
		System.out.println(Arrays.toString(s.solution(new String[]{"down", "down", "down", "down", "down", "up"}, new int[]{7, 9})));
		System.out.println(Arrays.toString(s.solution2(new String[]{"down", "down", "down", "down", "down", "up"}, new int[]{7, 9})));
	}

	public int[] solution(String[] keyinput, int[] board) {
		Map<String, int[]> map = new HashMap<String, int[]>(){
			{
				put("up", new int[]{0, 1});
				put("down", new int[]{0, -1});
				put("left", new int[]{-1, 0});
				put("right", new int[]{1, 0});
			}
		};

		int[] answer = new int[2];

		for (String s : keyinput) {
			int[] step = new int[2];

			for (int i = 0; i < answer.length; i++) {
				step[i] = step[i] + map.get(s)[i];
				answer[i] += Math.abs(answer[i] + step[i]) > board[i] / 2 ? 0 : step[i];
			}
		}

		return answer;
	}

	private int[] solution2(String[] keyinput, int[] board) {
		Map<String, int[]> map = new HashMap<String, int[]>(){
			{
				put("up", new int[]{0, 1});
				put("down", new int[]{0, -1});
				put("left", new int[]{-1, 0});
				put("right", new int[]{1, 0});
			}
		};

		return Arrays.stream(keyinput).map(map::get).reduce((arr1, arr2) -> new int[]{
				Math.abs(arr1[0] + arr2[0]) > board[0] / 2 ? board[0] / 2 * (arr1[0] < 0 ? -1 : 1) : arr1[0] + arr2[0] ,
				Math.abs(arr1[1] + arr2[1]) > board[1] / 2 ? board[1] / 2 * (arr1[1] < 0 ? -1 : 1) : arr1[1] + arr2[1]
		}).orElse(new int[]{});
	}

}










