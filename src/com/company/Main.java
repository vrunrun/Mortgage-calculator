// Specify what package this class belongs to
package com.company;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    // Constants
    final byte MONTHS_IN_YEAR = 12;
    final byte PERCENT = 100;

    int principal = 0;
    float monthlyInterestRate = 0F;
    short numberOfPayments = 0;

    Scanner scanner = new Scanner(System.in);

    while(true){
      System.out.print("Principal ($1K - $1M): ");
      principal = (int) scanner.nextInt();
      if (principal >= 1000 && principal <= 1_000_000)
        break;
      System.out.println("Enter a number between 1,000 and 1,000,000");
    }

    while (true) {
      System.out.print("Annual Interest Rate (1% - 30%): ");
      float annualInterestRate = scanner.nextFloat();
      if (annualInterestRate > 1 && annualInterestRate < 30) {
        monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR / PERCENT;
        break;
      }
      System.out.println("Enter a number between 1 and 30");
    }

    while(true) {
      System.out.print("Period (Years) (1 - 30): ");
      short numberOfYears = (short) scanner.nextShort();
      if (numberOfYears >= 1 && numberOfYears <= 30) {
        numberOfPayments = (short) (numberOfYears * MONTHS_IN_YEAR);
        break;
      }
      System.out.println("Enter a number between 1 and 30");
    }

    // Formula source: https://www.wikihow.com/Calculate-Mortgage-Payments
    double monthlyMortgageAmount = (principal *
        monthlyInterestRate * Math.pow(1+monthlyInterestRate, numberOfPayments) /
        (Math.pow(1+monthlyInterestRate, numberOfPayments) - 1));

    System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(monthlyMortgageAmount));
  }
}
