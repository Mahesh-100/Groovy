package com.conditions

class Leap {
//    Integer year
//
//    Leap(Integer year) {
//        this.year = year
//    }

    def static isLeapYear(year) {
        if (year % 4 === 0 &&year % 100 !== 0 || year % 400 ===0 ) {
           return true
        } else
            return false
    }
    static void main(String... args){
        //def year=new Leap(2000)
        println(isLeapYear(2000))
    }
}
