package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
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
     * @throws IllegalArgumentException if amount is negative or larger than balance
     */
    public void withdraw(double amount) {
        if (amount > balance || amount < 0) {
            throw new IllegalArgumentException("invalid amount, please try again");
        }
        else {
            balance -= amount;
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
     * @return true if amount is positive and has two or less decimal places, and false otherwise
     */
    public static boolean isAmountValid(double amount) {
        return false;
    }

    }


