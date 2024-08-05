package com.conditions

import groovy.transform.ToString

@ToString
class Employee {
    String name
    int age
    double salary
    String department

    Employee(String name, int age, double salary, String department) {
        this.name = name
        this.age = age
        this.salary = salary
        this.department = department
    }
}
