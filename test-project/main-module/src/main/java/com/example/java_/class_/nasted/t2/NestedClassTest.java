package com.example.java_.class_.nasted.t2;

public class NestedClassTest {
	private int aa = 20;
	static private int bb = 30;

	public NestedClassTest() {
	}

	//정적 중첩 클래스
	static class StaticInnerClass {
		public int a = 10;

		static public int b = 10;

		public StaticInnerClass() {
		}

		public int getAA() { //Static 변수만 접근 가능
			return bb;
		}
	}

	//중첩 클래스
	class InnerClass {
		public int a = 10;

		//static public int b = 10; //Static 선언 안됨
		public InnerClass() {
		}

		public int getAA() {
			return aa;
		}
	}

	private class PrivateClass{
	}

	//지역 중첩 클래스를 위한 메소드
	void methodLocalInnerClass() {
		class LocalInnerClass {
			int a = 10;   //static int b = 20; //Static선언 안됨
		}
		System.out.println("Local Inner Class: " + new LocalInnerClass().a);
	}

	//익명 중첩 클래스를 위한 메소드
	void methodAnonymousInnerClass() {
		System.out.println("Anonymous Inner Class: ");
	}

	public static void main(String[] args) {
		NestedClassTest outer = new NestedClassTest() {
			//오버라이딩할때 aaa()를 참조
			void methodAnonymousInnerClass() {
				System.out.println("Anonymous Inner Class: " + aaa());
			}

			int aaa() {
				return 77;
			}
		};

		//중첩클래스들 선언 방식
		StaticInnerClass sinner = new NestedClassTest.StaticInnerClass();
		System.out.println("Static Inner Class: "+sinner.a);

		NestedClassTest.InnerClass inner = outer.new InnerClass();
		System.out.println("Inner Class: "+inner.a);

		outer.methodLocalInnerClass();
		outer.methodAnonymousInnerClass();
		//outer.aaa(); //사용불가

	}


}







