package com.example.java_._codingTest.otherTest.과제;

import java.util.ArrayList;

class Employee {
	private int yearsOfWork; // 근속년수
	private String rank; // 직급
	private String birthdate; // 생년월일
	private String gender; // 성별
	private String name; // 이름

	public Employee(String name, String birthdate, String gender, String rank, int yearsOfWork) {
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
		this.rank = rank;
		this.yearsOfWork = yearsOfWork;
	}

	public int getYearsOfWork() {
		return yearsOfWork;
	}

	public String getRank() {
		return rank;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	// 인센티브 계산
	public int calculateIncentive() {
		// 기본적으로는 0으로 설정
		return 0;
	}
}

class Programmer extends Employee {
	private int skillLevel;

	public Programmer(String name, String birthdate, String gender, int yearsOfWork, int skillLevel) {
		super(name, birthdate, gender, "Programmer", yearsOfWork);
		this.skillLevel = skillLevel;
	}

	@Override
	public int calculateIncentive() {
		// 프로그래머의 인센티브 계산: 기술 레벨에 따라 차등 설정
		return skillLevel * 1000;
	}
}

class Designer extends Employee {
	private int designExperience;

	public Designer(String name, String birthdate, String gender, int yearsOfWork, int designExperience) {
		super(name, birthdate, gender, "Designer", yearsOfWork);
		this.designExperience = designExperience;
	}

	@Override
	public int calculateIncentive() {
		// 디자이너의 인센티브 계산: 디자인 경력에 따라 차등 설정
		return designExperience * 800;
	}
}

class Planner extends Employee {
	private int projectCount;

	public Planner(String name, String birthdate, String gender, int yearsOfWork, int projectCount) {
		super(name, birthdate, gender, "Planner", yearsOfWork);
		this.projectCount = projectCount;
	}

	@Override
	public int calculateIncentive() {
		// 기획자의 인센티브 계산: 프로젝트 수에 따라 차등 설정
		return projectCount * 1200;
	}
}

class Main {
	public static int calculateTotalYearsOfWork(ArrayList<Employee> employees) {
		int totalYearsOfWork = 0;

		for (Employee employee : employees) {
			totalYearsOfWork += employee.getYearsOfWork();
		}

		return totalYearsOfWork;
	}

	public static void main(String[] args) {
		// 다형성을 통해 여러 종류의 직원 객체를 생성
		Programmer programmer1 = new Programmer("이수연", "1995-11-20", "여성", 3, 5);
		Programmer programmer2 = new Programmer("최동근", "1992-03-10", "남성", 6, 8);
		Programmer programmer3 = new Programmer("손주연", "1988-07-15", "여성", 4, 6);

		Designer designer1 = new Designer("강준석", "1993-09-05", "남성", 5, 3);
		Designer designer2 = new Designer("송채은", "1990-12-18", "여성", 7, 5);
		Designer designer3 = new Designer("신민준", "1987-04-25", "남성", 6, 4);

		Planner planner1 = new Planner("배채린", "1994-08-12", "여성", 8, 6);
		Planner planner2 = new Planner("김정호", "1991-02-28", "남성", 3, 2);
		Planner planner3 = new Planner("이유빈", "1989-06-22", "여성", 5, 8);

		// 다형성을 이용해 Employee 객체를 담는 ArrayList 생성
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(programmer1);
		employees.add(programmer2);
		employees.add(programmer3);
		employees.add(designer1);
		employees.add(designer2);
		employees.add(designer3);
		employees.add(planner1);
		employees.add(planner2);
		employees.add(planner3);

		// 전체 직원의 근속 년수 합 계산
		int totalYearsOfWork = calculateTotalYearsOfWork(employees);

		// 출력
		System.out.println("전체 직원의 근속 년수 합: " + totalYearsOfWork + "년");
	}
}