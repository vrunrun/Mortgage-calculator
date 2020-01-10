// Specify what package this class belongs to
package com.company;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
  final static byte MONTHS_IN_YEAR = 12;  // Add 'static' keyword
  final static byte PERCENT = 100;

  public static void main(String[] args) {
    int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
    float annualInterestRate = (float) readNumber("Annual Interest Rate (%): ", 1, 30);
    byte numberOfYears = (byte) readNumber("Period (Years): ", 1, 30);

    double mortgage = calculateMortgage(principal, annualInterestRate, numberOfYears);
    System.out.println();
    System.out.println("MORTGAGE");
    System.out.println("--------");
    System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));

    System.out.println();
    System.out.println("PAYMENT SCHEDULE");
    System.out.println("----------------");
    double payment = 0;
    for (short month = 1; month<= numberOfYears * MONTHS_IN_YEAR; month++) {
      payment = calculatePayment(principal, annualInterestRate, numberOfYears, month);
      System.out.println(month + ": " + NumberFormat.getCurrencyInstance().format(payment));
    }
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
      byte numberOfYears) {

    float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR / PERCENT;
    short numberOfPayments = (short) (numberOfYears * MONTHS_IN_YEAR);

    // Formula source: https://www.wikihow.com/Calculate-Mortgage-Payments
    double monthlyMortgageAmount = (principal *
        monthlyInterestRate * Math.pow(1+monthlyInterestRate, numberOfPayments) /
        (Math.pow(1+monthlyInterestRate, numberOfPayments) - 1));

    return monthlyMortgageAmount;
  }

  public static double calculatePayment(
      int principal,
      float annualInterestRate,
      short numberOfYears,
      short numberOfPaymentsMade
  ) {

    float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR / PERCENT;
    short numberOfPayments = (short) (numberOfYears * MONTHS_IN_YEAR);

    double payment = principal *
        ((Math.pow((1 + monthlyInterestRate), numberOfPayments) - Math.pow((1 + monthlyInterestRate), numberOfPaymentsMade)) /
        (Math.pow((1 + monthlyInterestRate), numberOfPayments) -1));

    return payment;
    }
}
