package com.example.java_.z.코테.코테3;

public class User implements Runnable {
	private int id;
	private int currentFloor;
	private int destinationFloor;
	private Elevator elevator;

	public User(int id, int currentFloor, int destinationFloor, Elevator elevator) {
		this.id = id;
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.elevator = elevator;
	}

	@Override
	public void run() {
		System.out.println("User " + id + " is on floor " + currentFloor);
		if (currentFloor != destinationFloor) {
			// 현재 층과 목적지 층이 다르면 엘리베이터 호출
			callElevator();
		} else {
			System.out.println("User " + id + " is already on the destination floor");
		}
	}

	private void callElevator() {
		synchronized (elevator) {
			int elevatorCurrentFloor = elevator.getCurrentFloor();
			boolean elevatorMovingUp = elevator.isMovingUp();
			if (elevatorCurrentFloor == currentFloor && elevatorMovingUp == elevatorMovingUp) {
				System.out.println("User " + id + " is entering the elevator");
				elevator.setStopFloor(destinationFloor); // 엘리베이터에 멈춰야 할 층 설정
				elevator.moveToFloor(destinationFloor);
				System.out.println("User " + id + " has reached the destination floor");
			} else {
				System.out.println("User " + id + " is waiting for the elevator");
				try {
					elevator.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("User " + id + " is entering the elevator");
				elevator.setStopFloor(destinationFloor); // 엘리베이터에 멈춰야 할 층 설정
				elevator.moveToFloor(destinationFloor);
				System.out.println("User " + id + " has reached the destination floor");
			}
		}
	}
}