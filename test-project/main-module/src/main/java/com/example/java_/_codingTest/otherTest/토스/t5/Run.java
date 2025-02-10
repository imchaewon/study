package com.example.java_._codingTest.otherTest.토스.t5;

//정확성 시간 제한 / 메모리 제한
//10초/ 2GB

//부가가치세 계산기
//회계팀에서 개발팀에게 월간 부가가치세 자료를 요청하였습니다. 개발팀은 매월 구매영수증에 대한 부가가치세(VAT)를 계산하는 프로그램을 개발해야 하기에 회계팀과 회의를 아래와 같이 진행하였습니다.

//• 개발팀 : 그럼 부가가치세는 어떻게 계산해요?
//• 회계팀 : 부가가치세는요, 과세의 대상이 되는 과세금액에서 10%만큼 산정해주시면 됩니다. 참 고로 소수점(이하) 첫째자리에서 올림 처리합니다.
//• 개발팀 : 과세 금액은 어떻게 산정하면 될까요?
//• 회계팀 : 공급가액에서 비과세 금액을 빼주시면 됩니다. 비과세 금액은 말 그대로 부가가치세가 부과되지 않는 금액을 의미해요.
//• 개발팀 : 공급가액과 계산된 부가가치세를 합하면 주문금액과 동일한게 맞나요?
//• 회계팀 : 공급가액에 부가가치세를 더한 금액은 공급대가라고도 표현하는데, 보통 봉사료가 없 다면 공급대가는 주문금액과 같아요. 하지만 숙박업 등 봉사료가 존재하는 업종의 경우에는 주 문금액에서 봉사료를 제한 금액이 공급대가가 됩니다.
//• 개발팀 : 공급대가에서 비과세금액을 뺀 남은 금액이 1원이면, 부가가치세는 어떻게 되나요?
//• 회계팀 : 그 경우는 예외적으로 부가가치세를 0원으로 처리해 주세요.
//• 개발팀 : 자세한 설명 감사합니다!
//개발팀은 매출전표(거래를 증빙하는 표)로 부터 주문 금액, 비과세 금액, 그리고 봉사료를 알 수 있다 고 가정하겠습니다. 이 정보를 바탕으로 부가가치세를 계산하는 함수를 구현해 주세요.

//입력 예시
//solution(orderAmount, taxFreeAmount, serviceFee) 함수의 인자는 아래와 같이 전달됩니다.
//• 주문금액 : orderAmount ( 0 <= orderAmount<= 10000000)
//• 비과세금액 : taxFreeAmount ( 0 <= taxFreeAmount <= 10000000)
//• 봉사료 : serviceree (0 <= serviceFee <= 10000000)
//그리고 비과세금액과 봉사료의 합은 주문금액을 넘지 않습니다(taxFreeAmount + serviceFee <= orderAmount).

//출력 예시
//주문금액( orderAmount ) / 비과세금액( taxFreeAmount ) / 봉사료( serviceFee )에 따른 부 가가치세 금액( answer )을 반환합니다.
public class Run {
	public static void main(String[] args) {
		System.out.println(new Solution().solution(100, 0, 0));
		System.out.println(new Solution().solution(120, 20, 0));
		System.out.println(new Solution().solution(120, 0, 20));
	}
}

class Solution {
	public long solution(long orderAmount, long taxFreeAmount, long serviceFee) {
		// 공급가액 계산
		long supplyAmount = orderAmount - taxFreeAmount - serviceFee;

		// 부가가치세 계산
		double vat = supplyAmount * 0.1;

		// 소수점 이하 올림 처리
		long roundedVAT = (long) Math.ceil(vat);

		// 예외 처리: 만약 부가가치세가 1원일 경우 0으로 설정
		if (roundedVAT == 1 && orderAmount - taxFreeAmount == serviceFee) {
			roundedVAT = 0;
		}

		return roundedVAT;
	}
}