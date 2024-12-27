package com.example.java_._codingTest._programmers;

//문자열 my_string이 매개변수로 주어집니다. my_string은 소문자, 대문자, 자연수로만 구성되어있습니다.
//my_string안의 자연수들의 합을 return하도록 solution 함수를 완성해주세요.
public class Solution76 {
	public static void main(String[] args) {
		Solution76 s = new Solution76();
		System.out.println(s.solution("aAb1B2cC34oOp2"));
	}

	public int solution(String my_string) {
		int answer = 0;
		String[] arr = my_string.split("");
		String tmp = "0";
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].matches("[0-9]")){
				tmp += arr[i];
			}else{
				answer += Integer.parseInt(tmp);
				tmp = "0";
			}
			if(i == my_string.length() - 1)
				answer += Integer.parseInt(tmp);
		}
		return answer;
	}

}