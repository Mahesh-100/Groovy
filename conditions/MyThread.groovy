package com.conditions

class MyThread {


     static  void main(String... args){
         Thread.start{
            println Thread.currentThread().name
             println "Running in a new thread"
             Thread.sleep(1000)
             println "Thread finished"
         }
     }
}

