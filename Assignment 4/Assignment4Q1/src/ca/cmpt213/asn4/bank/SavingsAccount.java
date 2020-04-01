/**
 * This is a sub class of BankAccount class.
 * This class holds all the features that a
 * bank account holds in addition to some features
 * of Savings account.
 */
package ca.cmpt213.asn4.bank;

public class SavingsAccount extends BankAccount {

    protected boolean accountStatus;

    SavingsAccount(double bal,double roi){
        super(bal,roi);
        if (this.balance <=25){
            accountStatus=false;
        }
        else {
            accountStatus=true;
        }
    }

    @Override
    protected void Withdraw(double amount){
        if(accountStatus == true){
            super.Withdraw(amount);
            if(this.balance<=25){
                accountStatus=false;
            }
        }
        else {
            System.out.println("Account is not active. Withdrawl can not be made");
        }
    }
    @Override
    protected void Deposit(double amount) {
        super.Deposit(amount);
        if (this.balance > 25) {
            accountStatus = true;
        } else if(this.balance<=25){
            accountStatus = false;
        }
    }
    @Override
    protected void monthlyProcess(){
        if(this.numWithdrawls>4){
            this.monthlyServiceCharge=this.numWithdrawls-4;
        }
        super.monthlyProcess();
        if(this.balance<=25){
            this.accountStatus=false;
        }
    }
}
