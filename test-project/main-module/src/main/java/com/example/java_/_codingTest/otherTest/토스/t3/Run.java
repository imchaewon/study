package com.example.java_._codingTest.otherTest.토스.t3;

//정확성 시간 제한 / 메모리 제한
//10초/ 2GB

//가짜 영수증 찾기
//누군가가 가짜 영수증을 제출하고 있습니다. 김토스는 재무팀을 돕기 위해 가짜 영수증을 찾아내는 시스템을 만들고 싶습니다.
//가짜 영수증을 찾아내는 방법은 간단합니다. 가짜 영수증에는 금액이 옳지 않게 적혀있습니다. 옳은 금액은 다음의 조건을 모두 만족합니다.
//• 옳은 금액은 0~9 사이의 숫자 또는 구분자(,)로만 구성되어야 한다. 예를 들면 1만원 이나 10,000원, +300 은 0~9 사이의 숫자 또는 구분자(,)가 아닌 문자가 포함되어 있으므로 옳지 않은 금액이다.
//• 금액이 0원인 경우를 제외하고는 가장 왼쪽 숫자가 0 일 수 없다. 예를 들면, 0은 옳은 금액 이지만, 0100은 옳지 않다.
//• 금액은 세자리 구분자( , )를 포함하고 있거나, 또는 전혀 포함하고 있지 않다. 예를 들면, 구분 자를 전혀 포함하지 않은 금액 39900도 옳은 금액이다.
//• 세자리 구분자는 가장 오른쪽 숫자로부터 시작해 왼쪽 방향으로 매 3개의 숫자마다 1개의 구분 자(,)가 등장하는 형태이다. 예를 들면, 25,000, 123은 옳은 금액이고, 24,999,99는 옳지 않은 금액이다.
//• 가장 왼쪽 끝이나 오른쪽 끝에는 구분자를 두지 않는다. 예를 들면, "10,000," 과 ",999,000" 은 모두 옳지 않다.
//영수증에 적혀있는 금액을 보고 가짜 영수증인지 진짜 영수증인지 판단하는 시스템을 만들어주세요.

//입력 예시
//solution( amountText)
//함수의 인자는 아래와 같이 전달됩니다.
//• 영수증에 적혀있는 금액: amountText(1 <= 'amountText의 길이' = 1000)

import java.util.regex.Pattern;

//출력 예시
//주어진 amountText 가 옳은 금액이면 true , 옳지 않은 금액이면 false 를 반환합니다.
public class Run {
	public static void main(String[] args) {
		System.out.println(new Solution().solution("10000"));
		System.out.println(new Solution().solution("25,000"));
		System.out.println(new Solution().solution("39,00"));
	}
}

class Solution {
	public boolean solution(String amountText) {
		// 금액이 0원인 경우를 제외하고는 가장 왼쪽 숫자가 0 일 수 없음
		if (!amountText.equals("0") && amountText.startsWith("0")) {
			return false;
		}

		// 금액에 0~9 사이의 숫자 또는 구분자(,)로만 구성되어야 함
		if (!Pattern.matches("[0-9,]+", amountText)) {
			return false;
		}

		// 세자리 구분자(,)는 가장 오른쪽 숫자로부터 시작해 왼쪽 방향으로 매 3개의 숫자마다 1개의 구분자(,)가 등장하는 형태여야 함
		if (amountText.contains(",")) {
			int commaIndex = amountText.lastIndexOf(",");
			int digitsAfterComma = amountText.length() - commaIndex - 1;
			if (commaIndex == -1 || (digitsAfterComma % 4 != 0 && digitsAfterComma % 4 != 3)) {
				return false;
			}
		}

		// 가장 왼쪽 끝이나 오른쪽 끝에는 구분자를 두지 않음
		if (amountText.startsWith(",") || amountText.endsWith(",")) {
			return false;
		}

		return true;
	}
}