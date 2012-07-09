package com.demo.card;
//--------------------------------------------------------- 
// Checks for valid credit card number using Luhn algorithm 
//--------------------------------------------------------- 

public abstract class LuhnCheck {

	// --------------------------------
	// Filter out non-digit characters
	// --------------------------------

	private static String getDigitsOnly(String s) {
		StringBuffer digitsOnly = new StringBuffer();
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isDigit(c)) {
				digitsOnly.append(c);
			}
		}
		return digitsOnly.toString();
	}

	// -------------------
	// Perform Luhn check
	// -------------------

	public static boolean isValid(String cardNumber) {
		String digitsOnly = getDigitsOnly(cardNumber);
		int sum = 0;
		int digit = 0;
		int addend = 0;
		boolean timesTwo = false;

		for (int i = digitsOnly.length() - 1; i >= 0; i--) {
			digit = Integer.parseInt(digitsOnly.substring(i, i + 1));
			if (timesTwo) {
				addend = digit * 2;
				if (addend > 9) {
					addend -= 9;
				}
			} else {
				addend = digit;
			}
			sum += addend;
			timesTwo = !timesTwo;
		}

		int modulus = sum % 10;
		return modulus == 0;
	}

	// -----
	// Test
	// -----
	public static void main(String[] args) {
		String cardNumber = "4408 0412 3456 7893";
		boolean valid = LuhnCheck.isValid(cardNumber);
		System.out.println(cardNumber + ": " + valid);
		cardNumber = "4408 0412 3456 7893";
		valid = LuhnCheck.isValid(cardNumber);
		System.out.println(cardNumber + ": " + valid);
		cardNumber = "4417 1234 5678 9112";
		valid = LuhnCheck.isValid(cardNumber);
		System.out.println(cardNumber + ": " + valid);
		cardNumber = "4417 1234 5678 9113";
		valid = LuhnCheck.isValid(cardNumber);
		System.out.println(cardNumber + ": " + valid);
		
		
		cardNumber = "4392 2450 0533 4675";
		valid = LuhnCheck.isValid(cardNumber);
		System.out.println(cardNumber + ": " + valid);
		cardNumber = "4392 2558 3453 4552";
		valid = LuhnCheck.isValid(cardNumber);
		System.out.println(cardNumber + ": " + valid);
	}
}
