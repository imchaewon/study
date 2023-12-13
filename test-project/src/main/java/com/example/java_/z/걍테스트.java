package com.example.java_.z;

public class 걍테스트 {
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
	}
}
