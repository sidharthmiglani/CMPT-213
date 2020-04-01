/**
 * This is the Super class that
 * holds all the general features
 * a Bank account holds.
 */
package ca.cmpt213.asn4.bank;

public class BankAccount {

    protected double balance;
    protected int numDeposits;
    protected int numWithdrawls;
    protected double annualInterestRate;
    protected int monthlyServiceCharge;

    BankAccount(double balance, double annualInterestRate) throws IllegalArgumentException{
        if (balance<0){
            throw new IllegalArgumentException("Balance can not be negative.");
        }
        if(annualInterestRate<0){
            throw new IllegalArgumentException("Interest rate can not be negative");
        }
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    protected void Deposit (double amount) throws IllegalArgumentException{
        if(amount <=0){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance + amount;
        numDeposits++;
    }

    protected void Withdraw (double amount) throws IllegalArgumentException{
        if(amount<=0){
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if(amount>this.balance){
            throw new IllegalArgumentException("Withdrawl amount can't be " +
                    "more than your remaining balance ");
        }
        this.balance = this.balance - amount;
        numWithdrawls++;
    }

    protected void CalcInterest (){
        double monthlyInterestRate = this.annualInterestRate/12;
        double monthlyInterest = this.balance * (monthlyInterestRate/100);
        this.balance = this.balance + monthlyInterest;
    }

    protected void monthlyProcess(){
        this.balance = this.balance - this.monthlyServiceCharge;
        CalcInterest();
        numWithdrawls=0;
        numDeposits=0;
        monthlyServiceCharge=0;
    }
}
