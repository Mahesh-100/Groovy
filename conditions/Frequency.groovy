package com.conditions

class Frequency {

static void main(String[] args){
     def array = [3,5,6,7,9] as int[]
    altPrimeNums(array)
}



static void altPrimeNums(int[] arr){
    int primeCount=0
    int[] primeArray=new int[arr.length]
    for (i in 0..<arr.length) {
        int count=0
        for (j in 0..<arr.length) {
             if(arr[i]%j==0) {
                 count++
             }
        }
        if(count==2){
            primeArray[primeCount]=arr[i]
            primeCount++
        }
    }
    (0..<primeCount).step(2).each { i ->
        println primeArray[i]
    }

}

}
