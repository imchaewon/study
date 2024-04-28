package com.example.java_.z.클린코드.t1.클래스를나눈프로그램;

public class PrimePrinter {
	public static void main(String[] args) {
		final int NUMBER_OF_PRIMES = 1000;
		int[] primes = PrimeGenerator.generate(NUMBER_OF_PRIMES);

		final int ROWS_PER_PAGE = 50;
		final int COLUMNS_PER_PAGE = 4;
		RowColumnPagePrinter tablePrinter =
				new RowColumnPagePrinter(ROWS_PER_PAGE,
						COLUMNS_PER_PAGE,
						"The First " + NUMBER_OF_PRIMES +
								"Prime Numbers");
		tablePrinter.print(primes);
	}
}