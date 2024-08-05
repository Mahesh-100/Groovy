package com.conditions

class Frequency {

static void main(String[] args){
//     def array = [3,5,6,7,9]
//     assert array instanceof List
//     assert array.size() == 5
     println sum(3, 4)
     println sum(-3, -4)

}

    static def sum(int a, int b) {
          int result = a + b
          assert result >= 0 : "Result should be non-negative"
          return result
     }








}
