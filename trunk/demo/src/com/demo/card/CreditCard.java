package com.demo.card;
import java.util.Scanner;

public class CreditCard {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter Credit Card Number for Validation: ");
		long number = input.nextLong();

		long total = sumOfEvenPlaces(number) + sumOfOddPlaces(number);

		if (isValid(total)) {
			System.out.println(number + " is valid");
		} else {
			System.out.println(number + " is invalid");
		}
	}

	public static boolean isValid(long total) {
		if (total % 10 == 0) {
			return true;
		}
		return false;
	}

	public static int sumOfEvenPlaces(long number) {
		int sum = 0;
		int remainder;
		number %= 10;
		while (number % 10 != 0 || number / 10 != 0) {
			remainder = (int) (number % 10);

			sum = sum + getDigit(remainder * 2);
			number /= 100;
		}
		return sum;
	}

	public static int getDigit(int number) {
		if (number > 9) {
			return (number % 10 + number / 10);
		}
		return number;
	}

	public static int sumOfOddPlaces(long number) {
		int sum = 0;
		int remainder;
		number /= 10;
		while (number % 10 != 0 || number / 10 != 0) {
			remainder = (int) (number % 10);
			sum = sum + getDigit(remainder * 2);
			number /= 100;
		}
		return sum;
	}
}

