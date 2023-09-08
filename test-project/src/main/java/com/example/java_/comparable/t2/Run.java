package com.example.java_.comparable.t2;

import lombok.Getter;

public class Run {
	public static void main(String[] args) {
		Car c1 = new Car("아반떼", 2016, "노란색");
		Car c2 = new Car("소나타", 2010, "흰색");

		if (c1.compareTo(c2) == -1) {
			System.out.printf("%s 이(가) %s 보다 구식임",c1.getModelName(),c2.getModelName());
		} else if (c1.compareTo(c2) == 1) {
			System.out.printf("%s 이(가) %s 보다 신식임",c1.getModelName(),c2.getModelName());
		} else {
			System.out.println("연식이 동일함");
		}

	}

	@Getter
	static class Car implements Comparable<Car> {
		private String modelName;
		private int modelYear;
		private String color;

		public Car(String mn, int my, String c) {
			this.modelName = mn;
			this.modelYear = my;
			this.color = c;
		}

		public String getModel() {
			return modelYear + "식" + modelName + " " + color;
		}

		@Override
		public int compareTo(Car o) {
			if (modelYear == o.modelYear) {
				return 0;
			} else if (modelYear < o.modelYear) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
