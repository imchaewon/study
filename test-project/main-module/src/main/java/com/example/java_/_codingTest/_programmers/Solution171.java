package com.example.java_._codingTest._programmers;

//햄버거 만들기
//햄버거 가게에서 일을 하는 상수는 햄버거를 포장하는 일을 합니다. 함께 일을 하는 다른 직원들이 햄버거에 들어갈 재료를 조리해 주면 조리된 순서대로 상수의 앞에 아래서부터 위로 쌓이게 되고,
//상수는 순서에 맞게 쌓여서 완성된 햄버거를 따로 옮겨 포장을 하게 됩니다. 상수가 일하는 가게는 정해진 순서(아래서부터, 빵 – 야채 – 고기 - 빵)로 쌓인 햄버거만 포장을 합니다.
//상수는 손이 굉장히 빠르기 때문에 상수가 포장하는 동안 속 재료가 추가적으로 들어오는 일은 없으며, 재료의 높이는 무시하여 재료가 높이 쌓여서 일이 힘들어지는 경우는 없습니다.
//
//예를 들어, 상수의 앞에 쌓이는 재료의 순서가 [야채, 빵, 빵, 야채, 고기, 빵, 야채, 고기, 빵]일 때, 상수는 여섯 번째 재료가 쌓였을 때,
// 세 번째 재료부터 여섯 번째 재료를 이용하여 햄버거를 포장하고, 아홉 번째 재료가 쌓였을 때, 두 번째 재료와 일곱 번째 재료부터 아홉 번째 재료를 이용하여 햄버거를 포장합니다.
// 즉, 2개의 햄버거를 포장하게 됩니다.
//
//상수에게 전해지는 재료의 정보를 나타내는 정수 배열 ingredient가 주어졌을 때, 상수가 포장하는 햄버거의 개수를 return 하도록 solution 함수를 완성하시오.
// 1:빵 2:야채 3:고기

import java.util.Stack;

public class Solution171 {
	public static void main(String[] args) {
		Solution171 s = new Solution171();
		System.out.println(s.solution(new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1})); // 2
		System.out.println(s.solution(new int[]{1, 3, 2, 1, 2, 1, 3, 1, 2})); // 0
	}

	public int solution(int[] ingredient) {
		int answer = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i : ingredient) {
			stack.push(i);
			int size = stack.size();
			if(size >= 4){
				if(stack.get(size - 4) == 1 &&
					stack.get(size - 3) == 2 &&
					stack.get(size - 2) == 3 &&
					stack.get(size - 1) == 1){
					stack.pop();
					stack.pop();
					stack.pop();
					stack.pop();
					answer++;
				}
			}
		}
		return answer;
	}

	public int solution2(int[] ingredient) {
		int[] stack = new int[ingredient.length];
		int sp = 0;
		int answer = 0;
		for (int i : ingredient) {
			stack[sp++] = i;
			if (sp >= 4 && stack[sp - 1] == 1
				&& stack[sp - 2] == 3
				&& stack[sp - 3] == 2
				&& stack[sp - 4] == 1) {
				sp -= 4;
				answer++;
			}
		}
		return answer;
	}
}