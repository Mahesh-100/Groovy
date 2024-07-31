package com.conditions

class BigEvenDigit {
 static void main(String[] args){

        print(bigEvenDigit(724))
    }


    static int bigEvenDigit(int number){
        int max=0
        while(number!=0){
            int digit=number%10
            if(digit%2==0 && max<digit){
                  max=digit
             }
            number = number / 10 as int
        }
        return max
    }
}
