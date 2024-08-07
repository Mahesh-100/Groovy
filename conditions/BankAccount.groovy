package com.conditions

import java.util.concurrent.locks.ReentrantLock

class BankAccount {
    private double balance
    private final ReentrantLock lock = new ReentrantLock()

    BankAccount(double initialBalance) {
        this.balance = initialBalance
    }


    void deposit(double amount) {
        lock.lock()
        try {
            balance += amount
        } finally {
            lock.unlock()
        }
    }


    void withdraw(double amount) {
        lock.lock()
        try {
            if (balance >= amount) {
                balance -= amount
            }
        } finally {
            lock.unlock()
        }
    }

    double getBalance() {
        return balance
    }


    static void main(String...args){

    def account = new BankAccount(1000)

    def depositTask = {

            account.deposit(10)

    }

    def withdrawTask = {

            account.withdraw(50)

    }

   // Thread.start ( depositTask )
    Thread.start ( withdrawTask )
   Thread.start ( depositTask )
//    Thread.start ( withdrawTask )

// Allow threads to finish
    Thread.sleep ( 5000 )

    println " Final Balance:  ${account.getBalance() } "
}
}