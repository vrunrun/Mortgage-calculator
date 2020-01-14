package com.company;

public class MortgageCalculator {
  private final static byte MONTHS_IN_YEAR = 12;
  private final static byte PERCENT = 100;

  private int principal;
  private float annualInterestRate;
  private byte numberOfYears;

  public MortgageCalculator(int principal, float annualInterestRate, byte numberOfYears) {
    this.principal = principal;
    this.annualInterestRate = annualInterestRate;
    this.numberOfYears = numberOfYears;
  }

  public double calculateBalance(short numberOfPaymentsMade) {
    float monthlyInterestRate = getMonthlyInterestRate();
    short numberOfPayments = getNumberOfPayments();

    // Formula source: https://www.mtgprofessor.com/formulas.htm
    double balance = principal *
        ((Math.pow((1 + monthlyInterestRate), numberOfPayments) - Math.pow((1 + monthlyInterestRate), numberOfPaymentsMade)) /
        (Math.pow((1 + monthlyInterestRate), numberOfPayments) -1));

    return balance;
  }

  public double calculateMortgage() {
    float monthlyInterestRate = getMonthlyInterestRate();
    short numberOfPayments = getNumberOfPayments();

    // Formula source: https://www.wikihow.com/Calculate-Mortgage-Payments
    double monthlyMortgageAmount = (principal *
        monthlyInterestRate * Math.pow(1+monthlyInterestRate, numberOfPayments) /
        (Math.pow(1+monthlyInterestRate, numberOfPayments) - 1));

    return monthlyMortgageAmount;
  }

  public double[] getRemainingBalances() {
    var balances = new double[getNumberOfPayments()];
    for (short month = 1; month<= balances.length; month++)
      balances[month - 1] = calculateBalance(month);
    return balances;
  }

  private short getNumberOfPayments() {
    return (short) (numberOfYears * MONTHS_IN_YEAR);
  }

  private float getMonthlyInterestRate() {
    return annualInterestRate / MONTHS_IN_YEAR / PERCENT;
  }
}
