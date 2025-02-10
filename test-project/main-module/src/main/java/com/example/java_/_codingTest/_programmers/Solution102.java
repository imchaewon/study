package com.example.java_._codingTest._programmers;

//안전지대
//다음 그림과 같이 지뢰가 있는 지역과 지뢰에 인접한 위, 아래, 좌, 우 대각선 칸을 모두 위험지역으로 분류합니다.
//image.png
//지뢰는 2차원 배열 board에 1로 표시되어 있고 board에는 지뢰가 매설 된 지역 1과, 지뢰가 없는 지역 0만 존재합니다.
//지뢰가 매설된 지역의 지도 board가 매개변수로 주어질 때, 안전한 지역의 칸 수를 return하도록 solution 함수를 완성해주세요.
public class Solution102 {
	public static void main(String[] args) {
		Solution102 s = new Solution102();
//		System.out.println(s.solution(new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}}));
//		System.out.println(s.solution(new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 0, 0}}));
//		System.out.println(s.solution(new int[][]{{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}}));
		System.out.println(s.solution(new int[][]{{0, 0, 0, 0, 1},{0, 1, 0, 1, 0},{0, 1, 0, 0, 0},{0, 0, 1, 0, 0},{0, 1, 0, 0, 0}}));
	}

	public int solution(int[][] board) {
		int answer = 0;

//		{0, 0, 0, 0, 1},
//		{0, 1, 0, 1, 0},
//		{0, 1, 0, 0, 0},
//		{0, 0, 1, 0, 0},
//		{0, 1, 0, 0, 0}

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (!(board[i][j] == 1 ||
					i != 0 && (
						board[i - 1][j] == 1 ||
						j != 0 && board[i][j - 1] == 1 ||
						j != 0 && board[i - 1][j - 1] == 1 ||
						j != board.length - 1 && board[i - 1][j + 1] == 1)
					||
				j != board[0].length - 1 && board[i][j + 1] == 1 ||
				i != board.length - 1 && (
					board[i + 1][j] == 1 ||
					j != board.length - 1 && board[i + 1][j + 1] == 1 ||
					j != 0 && board[i + 1][j - 1] == 1)
				)) answer++;

		return answer;
	}

}

//				if(board[i][j] == 1)
//					continue;
//				if(i != board.length - 1 && board[i+1][j] == 1 || j != board[0].length - 1 && board[i][j+1] == 1)
//					continue;
//				if(i != 0 && board[i-1][j] == 1 || j != 0 && board[i][j-1] == 1)
//					continue;
//				if(i != 0 && j != 0 && board[i-1][j-1] == 1)
//					continue;
//				if(i != 0 && j != board.length - 1 && board[i-1][j+1] == 1)
//					continue;
//				if(i != board.length - 1 && j != board.length - 1 && board[i+1][j+1] == 1)
//					continue;
//				if(i != board.length - 1 && j != 0 && board[i+1][j-1] == 1)
//					continue;