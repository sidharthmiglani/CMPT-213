/**
 * This class tests all the methods
 * of SavingsAccount and BankAccount
 * class using JUnit3.
 */
package ca.cmpt213.asn4.bank;

import junit.framework.TestCase;

public class SavingsAccountTest extends TestCase {

    SavingsAccount acct;

    public void testWithdraw() {
        acct = new SavingsAccount(50,10);
        acct.Withdraw(5);
        assertEquals(acct.balance,45.0);
        try{
            acct.Withdraw(-100);
        }catch (IllegalArgumentException e){}
        assertEquals(acct.numWithdrawls,1);
        try{
            acct.Withdraw(100002);
            fail();
        }catch (IllegalArgumentException e){}

    }
    public void testConstructor() {
        acct=new SavingsAccount(30,2);
        assertEquals(30.0,acct.balance);
        assertEquals(2.0,acct.annualInterestRate);

    }
    public void testConstructorWithExceptions() {
        try{
            acct=new SavingsAccount(-1,0);
            fail();
            assertEquals(acct.accountStatus,false);
        }catch (IllegalArgumentException e){}

    }
    public void testDeposit(){
        acct = new SavingsAccount(10,2);
        acct.Deposit(5);
        assertEquals(acct.balance,15.0);
        assertEquals(acct.accountStatus,false);
        acct.Deposit(20);
        assertEquals(acct.accountStatus,true);
        assertEquals(acct.numDeposits,2);
        try{
            acct.Deposit(-1);
            fail();
        }catch (IllegalArgumentException e){}
        acct.Withdraw(20);
        acct.Deposit(5);
        assertFalse(acct.accountStatus);
    }
    public void testCalcInterest(){
        acct=new SavingsAccount(50,12);
        acct.CalcInterest();
        assertEquals(acct.balance,50.5);
    }
    public void testMonthlyProcess() {
        acct=new SavingsAccount(162,12);
        acct.Withdraw(10);
        acct.Withdraw(10);
        acct.Withdraw(10);
        acct.Withdraw(10);
        acct.Withdraw(10);
        acct.Withdraw(10);
        acct.monthlyProcess();
        assertEquals(acct.balance,101.0);
        assertEquals(acct.numDeposits,0);
        assertEquals(acct.numWithdrawls,0);
        assertEquals(acct.monthlyServiceCharge,0);
    }
}