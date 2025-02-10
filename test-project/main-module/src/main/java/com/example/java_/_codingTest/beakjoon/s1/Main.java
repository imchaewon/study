package com.example.java_._codingTest.beakjoon.s1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 현재 시각 입력
        int currentHour = scanner.nextInt();
        int currentMinute = scanner.nextInt();

        // 요리하는 데 필요한 시간 입력
        int cookingTime = scanner.nextInt();

        // 현재 시각을 총 분으로 환산
        int totalMinutes = currentHour * 60 + currentMinute;

        // 요리 시간을 더함
        totalMinutes += cookingTime;

        // 종료되는 시각 계산
        int endHour = (totalMinutes / 60) % 24;  // 24시간 형식으로 맞춤
        int endMinute = totalMinutes % 60;

        // 결과 출력
        System.out.println(endHour + " " + endMinute);
    }
}