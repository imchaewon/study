package com.example.java_.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.java_.type.C1.C2.*;

public class C1 {
	public static void main(String[] args) {
		System.out.println("s1 = null: " + (s1 == null));
		System.out.println("i1 = 0: " + (i1 == 0));
		System.out.println("l1 = 0: " + (l1 == 0));
		System.out.println("b1 = false: " + (b1 == false));
		System.out.println("c1 = 0: " + (c1 == 0));
		System.out.println("by1 = 0: " + (by1 == 0));
		System.out.println("sh1 = 0: " + (sh1 == 0));
		System.out.println("f1 = 0: " + (f1 == 0));
		System.out.println("d1 = 0: " + (d1 == 0));
		System.out.println("obj1 = null: " + (obj1 == null));
		System.out.println("list1 = null: " + (list1 == null));
		System.out.println("arrayList1 = null: " + (arrayList1 == null));
		System.out.println("arrayList2.isEmpty(): " + (arrayList2.isEmpty()));
		System.out.println("map1" + (map1.isEmpty()));
	}

	static class C2{
		static String s1;
		static int i1;
		static long l1;
		static boolean b1;
		static char c1;
		static byte by1;
		static short sh1;
		static float f1;
		static double d1;
		static Object obj1;
		static List list1;
		static ArrayList arrayList1;
		static ArrayList arrayList2 = new ArrayList();
		static Map map1 = new HashMap<>();
	}
}
