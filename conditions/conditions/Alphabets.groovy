package com.conditions

class Alphabets {

    static void main(String[] args) {
        alpha()
    }


    static void alpha(){
        ('A'..'Z').each{ ch->
            println "$ch"
        }
        ('a'..'z').each{ ch->
            println "$ch"
        }
    }
}
