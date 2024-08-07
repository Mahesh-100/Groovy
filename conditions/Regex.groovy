package com.conditions

class Regex {
    static void main(String... args){
        def pattern = ~/[A-Za-z0-9]{3}-\d{2}-\d{4}$/
        def num="A53-33-4444"
        println(num==~pattern)
        def text = "The rain in Spain stays mainly in the plain"
        def pattern1 = ~/ain/
        def matcher = text =~ pattern1

        println matcher.findAll()
    }
}
