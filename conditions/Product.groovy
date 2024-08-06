package com.conditions

import groovy.transform.Immutable
import groovy.transform.ToString

import java.util.stream.Collector
import java.util.stream.Collectors

@Immutable
//@ToString
class Product {
    String name
    String company
    double price

//    Product(String name, String company, double price) {
//        this.name = name
//        this.company = company
//        this.price = price
//    }
    String toString() {
        return "Product(name: ${name}, company: ${company}, price: ${price})"
    }

    static void main(String[] args) {
        def products = [
                new Product("Inspiron 15", "Dell", 55000),
                new Product("XPS 13", "Dell", 70000),
                new Product("Pavilion 14", "HP", 45000),
                new Product("MacBook Air", "Apple", 90000),
                new Product("Vostro 14", "Dell", 49000)
        ]


        //def  dellLaptops=products.stream().filter {it.company.equalsIgnoreCase("DELL")}.filter {it.price>50000}.collect(Collectors.toList())
        //dellLaptops.each {product-> println(product)}
        def dellLaptops = products.findAll() {
            it.company.equalsIgnoreCase("DELL") && it.price > 50000
        }
        println dellLaptops


    }


}
