/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstExercises;

/**
 *
 * @author MIPC
 */
public class CreditCard {
    private String customer;
    private String bank;
    private String account;
    private int limit;
    protected double balance;
    
    public CreditCard(String cust, String bk, String acnt, int lim, double initialBal){
        customer = cust;
        bank = bk;
        account = acnt;
        limit = lim;
        balance = initialBal;
    }
    public CreditCard(String cust, String bk, String acnt, int lim){
        this(cust,bk,acnt,lim,0.0);
    }
    public String getCustomer() { return customer; }
    public String getBank() { return bank;} 
    public String getAccount() { return account;}
    public int getLimit() { return limit; }
    public double getBalance() { return balance;} 
    
    public boolean charge(double price){
        if(price + balance > limit)
            return false;
        balance += price;
        return true;
    }
    public void makePayment(double amount){//Make a payment
        balance -= amount;
    }
    public String toString(CreditCard card){
        return("Customer = " + card.customer + "\n" +
                "Bank = " + card.bank + "\n" +
                "Account = " + card.account + "\n" +
                "Balance = " + card.balance + "\n" +
                "Limit = " + card.limit);
    }
    public static void main(String[] args){
        CreditCard[] wallet = new CreditCard[3];
        wallet[0] = new CreditCard("John Bowman.","California Saving",
                                    "5391 0375 9387 5309",5000);
        wallet[1] = new CreditCard("John Bowman.","California Federal",
                                    "3485 0399 3395 1954",3500);
        wallet[2] = new CreditCard("John Bowman.","California Finance",
                                    "5391 0375 9387 5309",2500,300);
        for(int val = 1; val <= 16;val++){
            wallet[0].charge(3*val);
            wallet[1].charge(2*val);
            wallet[2].charge(val);
        }
        
        for(CreditCard card:wallet){
            System.out.println(card.toString(card));
            while(card.getBalance() > 200.0){
                card.makePayment(200);
                System.out.println("New balance = " + card.getBalance());
            }
        }
    }
}
