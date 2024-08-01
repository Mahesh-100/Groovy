package com.conditions

class PerfectNumbers {
    enum Classification{
        PERFECT,ABUNDANT,DEFICIENT
    }
    static Classification classify(num){

        int sum=0
        for(int i = 1; i <= num / 2; i++){
            if(num%i==0){
                sum+=i
            }
        }
        if(sum==num){
            return Classification.PERFECT
        }else if(sum>num){
            return Classification.ABUNDANT
        }else
            return Classification.DEFICIENT
    }
    static void main(String[] args){
       println  classify(33550336)
    }
}
