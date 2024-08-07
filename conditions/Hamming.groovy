package com.conditions

class Hamming {
    static def distance(def strand1, def strand2) {
        if(strand1.length()!=strand2.length()){
            throw new IllegalArgumentException("DNA strands must be of equal length")
        }
        int distance=0
        for(int i=0;i<strand1.length();i++){
            if(strand1.charAt(i)!=strand2.charAt(i))
                distance++
        }
        return distance

    }
    static void main(String...args){
        String strand1 = "GAGCCTACTAACGGGAT"
        String strand2 = "CATCGTAACGACGGCCT"

        try {
            int distance = distance(strand1, strand2)
            println("The Hamming Distance between the two DNA strands is: $distance")
        } catch (IllegalArgumentException e) {
            println(e.message)
        }
    }
}
