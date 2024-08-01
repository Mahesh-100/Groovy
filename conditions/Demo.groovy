package com.conditions

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
        map['location'] = 'IND'
        map['name']='mahesh'

        println map
        def result = operate(5, 3, sum)
        println result

    }

    static def operate(int a, int b, Closure operation) {
        return operation(a, b)
    }

}



