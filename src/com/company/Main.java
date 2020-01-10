// Specify what package this class belongs to
package com.company;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
    float annualInterestRate = (float) readNumber("Annual Interest Rate (%): ", 1, 30);
    short numberOfYears = (short) readNumber("Period (Years): ", 1, 30);

    double mortgage = calculateMortgage(principal, annualInterestRate, numberOfYears);

    System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(mortgage));
  }

  public static double readNumber(String prompt, double min, double max) {
    Scanner scanner = new Scanner(System.in);
    double value;

    while(true){
      System.out.print(prompt);
      value = scanner.nextDouble();
      if (value >= min && value <= max)
        break;
      System.out.println("Enter a number between " + min + " and " + max);
    }
    return value;
  }

  public static double calculateMortgage(
      int principal,
      float annualInterestRate,
      short numberOfYears) {

    final byte MONTHS_IN_YEAR = 12;
    final byte PERCENT = 100;

    float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR / PERCENT;
    short numberOfPayments = (short) (numberOfYears * MONTHS_IN_YEAR);

    // Formula source: https://www.wikihow.com/Calculate-Mortgage-Payments
    double monthlyMortgageAmount = (principal *
        monthlyInterestRate * Math.pow(1+monthlyInterestRate, numberOfPayments) /
        (Math.pow(1+monthlyInterestRate, numberOfPayments) - 1));

    return monthlyMortgageAmount;
  }
}
