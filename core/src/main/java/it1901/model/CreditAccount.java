package it1901.model;

import java.util.Random;
import java.security.SecureRandom;

import it1901.Bank;
import it1901.util.AccountType;

public class CreditAccount extends Account {

    private double credtiLimit;
    private static final double DEFAULT_CREDIT_LIMIT = 200;
    
    public CreditAccount(User user, String name) {
        super(user, 0, AccountType.CREDIT, name);
        this.credtiLimit = DEFAULT_CREDIT_LIMIT;
    }

    public CreditAccount(String id, User user, double interestRate, String name, int accountNumber) {
        super(id, user, interestRate, AccountType.CREDIT, accountNumber, name);
        this.credtiLimit = DEFAULT_CREDIT_LIMIT;
    }

    @Override
    public double getCreditLimit() {
        return this.credtiLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw must be positive");
        if((this.getBalance() + this.credtiLimit) - amount < 0) throw new IllegalStateException("Not enough balance on account");
        
        this.setBalance(this.getBalance() - amount);
    }


    @Override
    public int generateAccountNumber() {
        Random rand = new SecureRandom();
        String accNumberString = "69";
        for (int i=0;i<3;i++) {
            accNumberString += String.valueOf(rand.nextInt(10));
        }
        int accNumber = Integer.parseInt(accNumberString);
        if (Bank.getInstanceBank().getAccounts().containsKey(accNumber)) {
            generateAccountNumber();
        }
        return accNumber;
    }

    @Override
    public String getAccountType() {
        return "Kredittkonto";
    }
    
}
