package com.conditions

class MyThread extends Thread {
    void run() {
        println "Thread ${Thread.currentThread().name} is running."
    }
}



