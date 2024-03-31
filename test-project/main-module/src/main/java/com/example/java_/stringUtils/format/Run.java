package com.example.java_.stringUtils.format;

//+ 연산자, String.format() 메서드 성능 비교
//+ 연산자와 String.format() 메서드는 문자열을 연결하는 데 사용됩니다. 성능에 있어서는 사용하는 환경과 상황에 따라 다를 수 있습니다. 일반적으로는 + 연산자가 간단하고 직관적이기 때문에 성능 면에서 더 효율적입니다.
//하지만 문자열을 많이 조합해야 할 때나 반복적으로 사용될 때는 String.format() 메서드를 사용하는 것이 좋습니다.
//성능 차이를 보기 위해서는 직접 측정을 해보는 것이 가장 좋습니다. 하지만 대부분의 경우에는 + 연산자가 더 빠르게 동작합니다.
//이는 String.format() 메서드가 내부적으로 문자열 버퍼를 사용하여 문자열을 조합하는 데 비해, + 연산자는 단순히 문자열을 연결하는 연산이기 때문입니다.
//또한, Java 9부터는 String.format() 메서드의 내부 구현이 개선되어 더 나은 성능을 제공합니다. 그러나 이러한 최적화는 모든 상황에서 적용되는 것은 아니며, 여전히 + 연산자가 간단하고 빠르게 작동합니다.
//따라서 간단한 문자열 조합에는 + 연산자를 사용하고, 서식이 있는 문자열을 생성해야 할 때나 성능이 중요한 상황에서는 String.format() 메서드를 사용하는 것이 좋습니다.
public class Run {
	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		System.out.println("a" + " " + "b");
//		long end = System.currentTimeMillis();
//		System.out.println(end - start);
//
//		long start2 = System.currentTimeMillis();
//		System.out.println(String.format("%s %s", "a", "b"));
//		long end2 = System.currentTimeMillis();
//		System.out.println(end2 - start2);
//
//		System.out.println("-----");

		long start3 = System.currentTimeMillis();
		System.out.println("a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " +
				"a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a" + " " + "a");
		long end3 = System.currentTimeMillis();
		System.out.println(end3 - start3);

		long start4 = System.currentTimeMillis();
		System.out.println(String.format("%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s " +
						"%s %s %s %s %s %s %s %s %s %s ",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a"));
		long end4 = System.currentTimeMillis();
		System.out.println(end4 - start4);

	}
}
