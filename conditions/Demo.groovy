package com.conditions
//import groovyx.gpars.GParsPool
class Demo {
    String name
    int age
    static def sum = { a, b -> a + b }
    static void main(String[] args) {
//        def arr=[]
//        println "Hello Mahesh"
//        println(10<<2)
//        arr <<5
//        println arr
//        def person = new Demo(name: 'John', age: 30)
//        println person.name
//        def now = new Date()
//        println now
        def map = [name: 'John', age: 30]

// Add or update a key-value pair
//        map['location'] = 'IND'
//        map['name']='mahesh'
//
//        println map
//        def result = operate(5, 3, sum)
//        println result

//        Thread.start {
//            println "Running in a new thread"
//            Thread.sleep(1000)
//            println "Thread finished"
//        }

        def thread1 = new MyThread()
        def thread2 = new MyThread()

        thread1.start()
        thread2.start()

        thread1.join() // Wait for thread1 to finish
        thread2.join() // Wait for thread2 to finish

//        GParsPool.withPool {
//            (1..5).eachParallel { println "Parallel processing: $it" }
//        }


    }

    static def operate(int a, int b, Closure operation) {
        return operation(a, b)
    }

}



