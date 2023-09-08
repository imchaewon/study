package com.example.java_.operatorPrecedence.t4;

public class Run {
	public static void main(String[] args) {

		String s1 = null;

		if (s1 == null || s1.equals("asd")){
			System.out.println(true);
		}else{
			System.out.println(false);
		}

		try{
			if (s1.equals("asd") || s1 == null){
				System.out.println(true);
			}else {
				System.out.println(false);
			}
		} catch (Exception e) {
			System.out.println("Run.main()");
			e.printStackTrace();
		}

		System.out.println("--------------------------------------------------");

		int i=0;

		if( true || i++ == 99 || (i=i+2) == 999){
		}

		System.out.println(i);

		if( true && i++ == 99 || (i=i+2) == 999){
		}

		System.out.println(i);

		if( false && i++ == 99 || (i=i+2) == 999){
		}

		System.out.println(i);


	}
}



