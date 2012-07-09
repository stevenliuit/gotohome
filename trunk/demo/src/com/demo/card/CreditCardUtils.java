package com.demo.card;
import java.util.Scanner;

public class CreditCardUtils {
	public static void main(String[] args) {
        int evenSum, oddSum;
        System.out.println("Hello!");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the credit card number without any spaces: ");
        long number = input.nextLong();
        if (isValid(number) == true) {
            evenSum = sumOfEvenPlace(number);
            oddSum = sumOfOddPlace(number);
                if(((evenSum + oddSum) % 10) == 0)
                    System.out.println("Number valid");
                else
                    System.out.println("Invalid");
        }
        else
            System.out.println("Invalid");
    }

	public static boolean isValid(long number) {
		if (((number / 1000000000000000L) <= 6) && ((number / 1000000000000000L) >= 4))
			return true;
		else if ((number / 1000000000000000L) < 4) {
			if ((number / 100000000000L) == 37)
				return true;
			else
				return false;
		} else
			return false;
	}

	public static int sumOfEvenPlace(long number) {
            int number2 = (int)number;
            int evenSum = 0;
            int evenDigit = 0;
            int x = 0;
            while (number > 0) {
                evenDigit = 2 * ((number2 % 100) / 10);
                number2 = number2 / 100;
                if (evenDigit > 10)
                    x = getDigit(evenDigit);
                else
                    x = evenDigit;
            evenSum = evenSum + x;
            }
            return evenSum;
    }

	public static int getDigit(int evenDigit) {
           int secondDigit = 0;
           secondDigit = evenDigit % 10;
           evenDigit = secondDigit + 10;
           return evenDigit;
    }

	public static int sumOfOddPlace(long number) {
            int number3 = (int)number;
            int oddSum = 0;
            int oddDigit = 0;
            int x = 0;
            while (number > 0) {
                oddDigit = (number3 % 10);
                number3 = number3 / 100;
            oddSum = oddSum + x;
            }
            return oddSum;
    }
}