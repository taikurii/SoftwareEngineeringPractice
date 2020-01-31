package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email or startingBalance is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (!(isEmailValid(email))) {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        else if(!(isAmountValid(startingBalance))) {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
        else {
            this.email = email;
            this.balance = startingBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if amount is larger than balance or invalid (negative or more than two decimal places)
     */
    public void withdraw(double amount) {
        if (amount > balance || amount < 0) {
            throw new IllegalArgumentException("Amount: " + amount + " is larger than balance, cannot withdraw");
        }
        else if (!(isAmountValid(amount))) {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot withdraw");
        }
        else {
            balance -= amount;
        }

    }

    /**
     * @post increases balance by amount if amount is valid (negative or more than two decimal places)
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit(double amount) {
        if(!(isAmountValid(amount))) {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot deposit");
        }
        else {
            balance += amount;
        }
    }

    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1 || email.indexOf('#') != -1 || email.indexOf('!') != -1) {
            return false;
        } else if (email.charAt(email.indexOf('@') - 1) == '-' || email.charAt(email.indexOf('@') - 1) == '.' || email.charAt(email.indexOf('@') - 1) == '_') {
            return false;
        } if (email.indexOf('-') != -1 || email.indexOf('.') != -1 || email.indexOf('_') != -1) {
            if (email.charAt(email.indexOf('-') + 1) == '-' || email.charAt(email.indexOf('-') + 1) == '.' || email.charAt(email.indexOf('-') + 1) == '_') {
                return false;
            } else if (email.charAt(email.indexOf('.') + 1) == '-' || email.charAt(email.indexOf('.') + 1) == '.' || email.charAt(email.indexOf('.') + 1) == '_') {
                return false;
            } else if (email.charAt(email.indexOf('_') + 1) == '-' || email.charAt(email.indexOf('_') + 1) == '.' || email.charAt(email.indexOf('_') + 1) == '_') {
                return false;
            } else if(email.charAt(email.indexOf('@') + 1) == '@') {
                return false;
            } else if(email.substring(email.lastIndexOf('.')+1).length() < 2) {
                return false;
            } else if(email.indexOf('.', email.indexOf('@')) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @post withdraws amount from accountFrom and deposits it into accountTo if amount is valid (negative or more than two decimal places)
     * @throws IllegalArgumentException is amount is larger than accountFrom's balance or invalid
     */
    public static void transfer(double amount, BankAccount accountFrom, BankAccount accountTo) {

    }

    /**
     * @return true if amount is positive and has two or less decimal places, and false otherwise
     */
    public static boolean isAmountValid(double amount) {
        if(amount <= 0) {
            return false;
        }
        double rounded = java.lang.Math.round(amount*100)/100D;
        if(rounded == amount) {
         return true;
        }
        else {
            return false;
        }
    }
}


