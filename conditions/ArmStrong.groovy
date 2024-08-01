package com.conditions;

 class ArmStrong {
    // Function to check if a number is an Armstrong number
    static def isArmstrongNumber(int number) {
        def digits = number.toString().collect { it as int }
        def numDigits = digits.size()
        def sumOfPowers = digits.sum {Math.pow(it,numDigits)}
        return sumOfPowers == number
    }


    static void main(String[] args) {


        def numbers = [45,153, 370, 371, 407, 9474, 9475]
        numbers.each { num ->
            if (isArmstrongNumber(num)) {
                println "$num is an Armstrong number"
            } else {
                println "$num is not an Armstrong number"
            }
        }


    }
}
