package com.conditions

class DifferenceOfSquares {
        int num
    DifferenceOfSquares(num) {
       this.num=num
    }
    def squareOfSum() {
        def sum=num*(num+1)/2
        return sum*sum
    }
    def sumOfSquares() {
        return (num*(num+1)*(2*num+1))/6
    }
    def difference() {
       return squareOfSum()-sumOfSquares()
    }

}
