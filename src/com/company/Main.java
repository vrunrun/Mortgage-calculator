// Specify what package this class belongs to
package com.company;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Avoid magic numbers in code
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        int principal = (int) scanner.nextInt();

        System.out.print("Annual Interest Rate: ");
        float monthlyInterestRate = scanner.nextFloat() / MONTHS_IN_YEAR / PERCENT;

        System.out.print("Period (Years): ");
        short numberOfPayment = (short) (scanner.nextShort() * MONTHS_IN_YEAR);

        // Formula source: https://www.wikihow.com/Calculate-Mortgage-Payments
        double monthlyMortgageAmount = (principal *
                monthlyInterestRate * Math.pow(1+monthlyInterestRate, numberOfPayment) /
                (Math.pow(1+monthlyInterestRate, numberOfPayment) - 1));

        System.out.println("Mortgage " + NumberFormat.getCurrencyInstance().format(monthlyMortgageAmount));
    }
}
