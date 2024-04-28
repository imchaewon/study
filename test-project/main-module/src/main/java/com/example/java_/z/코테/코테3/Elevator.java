package com.example.java_.z.코테.코테3;

public class Elevator {
	private int currentFloor;
	private boolean movingUp;
	private int stopFloor; // 멈춰야 할 층

	public Elevator() {
		currentFloor = 1; // 초기 층 설정
		movingUp = true; // 초기 방향 설정
		stopFloor = -1; // 초기 멈출 층 설정
	}

	public void moveToFloor(int targetFloor) {
		System.out.println("Elevator moving to floor " + targetFloor);
		// 엘리베이터가 targetFloor로 이동하는 시간이 소요될 것으로 가정
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		currentFloor = targetFloor;
		System.out.println("Elevator arrived at floor " + targetFloor);

		if (targetFloor == stopFloor) {
			stopFloor = -1; // 멈춘 층 초기화
			synchronized (this) {
				notifyAll(); // 대기 중인 사용자에게 알림
			}
		}
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public void setStopFloor(int stopFloor) {
		this.stopFloor = stopFloor;
	}
}