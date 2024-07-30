package com.example.java_.z.걍테스트;

public class C1 {
	public static void main(String[] args) {
		int arr[][] = {{88, 90, 65, 87, 98},
						{77, 80, 75, 97, 88},
						{90, 78, 77, 77, 92},
						{67, 91, 75, 85, 95},
						{89, 92, 69, 83, 91},
						{97, 93, 95, 97, 89},
						{94, 94, 65, 99, 87},
						{87, 80, 85, 91, 7},
						{84, 88, 87, 88, 78}};

		String subjectNames[] = {"math", "computer", "java", "english", "korean"};
		String studentNames[] = {"태조", "정증", "태증", "세증", "은증", "단증", "세조", "예증", "성증", "연산군"};

		System.out.println("\"과목별 평균은 다음과 같습니다!\"");
		int sum;
		for (int subject = 0; subject < arr[0].length; subject++) {
			sum = 0;
			for (int student = 0; student < arr.length; student++) {
				sum += arr[student][subject];
			}
			System.out.println(subjectNames[subject] + " 과의 평균은 " + (sum / arr.length) + "입니다");
		}

		System.out.println("\"학생별 평균은 다음과 같습니다!\"");
		for (int student = 0; student < arr.length; student++) {
			sum = 0;
			for (int subject = 0; subject < arr[0].length; subject++) {
				sum += arr[student][subject];
			}
			System.out.println(studentNames[student] + " 학생의 평균은 " + (sum / arr[0].length) + "입니다");
		}
//		System.out.println((int)(Math.random() * 2));
//
//		System.out.println();
//
//		for (int i = 9; i <= 11; i++) System.out.println(i);// 10월~12월
//		System.out.println();
//
//		for (int i = 9; i <= 12; i++) System.out.println(i);// 10월~1월(이월) 0+12
//		System.out.println();
//
//
//
//		for (int i = 9; i <= 12; i++) { // 10월~1월(이월) 0+12
//			System.out.println(i % 12);
//		}
//		System.out.println();
//
//		System.out.println((C1) null);
//		System.out.println(String.valueOf((C1) null).equals("null"));

		System.out.println(false && false);
		System.out.println(true && false);
		System.out.println(false & false);
		System.out.println(true & false);
		System.out.println();
		System.out.println(false || false);
		System.out.println(true || false);
		System.out.println(false | false);
		System.out.println(true | false);
		boolean b1 = true;
		boolean b2 = false;
		System.out.println(b1 |= b2);
		System.out.println(b1 &= b2);
	}
}