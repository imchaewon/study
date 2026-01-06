package com.example.java_._codingTest.beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S10 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[][] arr = new int[r][c];

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println("arr = " + Arrays.deepToString(arr));

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 1) {
					if(i != 0){
						if(arr[i - 1][j] != -1) {
							arr[i - 1][j] = 1;
						}
					}
					if(i != arr[i].length){
						if(arr[i + 1][j] != -1) {
							arr[i + 1][j] = 1;
						}
					}
					if(j != 0){
						if(arr[i + 1][j] != -1) {
							arr[i + 1][j] = 1;
						}
					}
					if(j != arr[j].length){

					}
				}
			}
		}
	}
}