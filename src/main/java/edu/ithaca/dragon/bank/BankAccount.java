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
     */
    public void withdraw(double amount) {
        balance -= amount;

    }


    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1 || email.indexOf('#') != -1 || email.indexOf('!') != -1) {
            return false;
        } else if (email.charAt(email.indexOf('@') - 1) == '-' || email.charAt(email.indexOf('@') - 1) == '.' || email.charAt(email.indexOf('@') - 1) == '_') {
            return false;
        } else if (email.indexOf('-') != -1 || email.indexOf('.') != -1 || email.indexOf('_') != -1) {
            String x = "";
            String y = "";
            for (int i = 0; i < email.length(); i++) {
                if ((x == "-" && y == "-") || (x == "." && y == ".") || (x == "_" && y == "_")) {
                    return false;
                } else if ((x == "-" && y == "_") || (x == "-" && y == ".") || (x == "." && y == "-") || (x == "." && y == "_")) {
                    return false;
                } else if ((x == "_" && y == "-") || (x == "_" && y == ".")) {
                    return false;
                }
            }
        }
        else if(email.charAt(0) == '.' || email.charAt(0) == '_' || email.charAt(0) == '-') {
            return false;
        }

    }
}

