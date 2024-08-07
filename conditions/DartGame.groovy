package com.conditions

class DartGame {
    static int score(x, y) {
        def distance=Math.sqrt(x*x+y*y)
        if(distance>10) return 0
        else if(distance>5) return 1
        else if(distance>1) return 5
        else  return 10

    }
}
