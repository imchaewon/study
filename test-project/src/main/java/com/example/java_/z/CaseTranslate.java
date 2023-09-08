package com.example.java_.z;

public class CaseTranslate {
	public static void main(String[] args) {
		String snakeCaseString = "HELLO_WORLD_EXAMPLE";
		String camelCaseString = convertSnakeTo(snakeCaseString, CASE.PASCAL);
		System.out.println(camelCaseString); // Output: helloWorldExample
	}

	public static String convertSnakeTo(String str, CASE _case) {
		StringBuilder camelCase = new StringBuilder();
		boolean capitalizeNext = (CASE.PASCAL == _case);

		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);

			if (currentChar == '_')
				capitalizeNext = true;
			else {
				if (capitalizeNext) {
					camelCase.append(Character.toUpperCase(currentChar));
					capitalizeNext = false;
				} else
					camelCase.append(Character.toLowerCase(currentChar));
			}
		}

		return camelCase.toString();
	}
}

enum CASE{
	CAMEL,
	PASCAL
}