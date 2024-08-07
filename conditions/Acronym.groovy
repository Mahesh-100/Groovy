package com.conditions

class Acronym {
    static String abbreviate(String phrase) {
        // Remove punctuation except hyphens
        def cleanedPhrase = phrase.replaceAll(/[^\w\s-]/, '')

        // Split the phrase into words based on spaces, hyphens, and underscores
        def words = cleanedPhrase.split(/[\s-_@]+/)

        // Create the acronym by taking the first letter of each word and converting to uppercase
        def acronym = words.collect { it[0].toUpperCase() }.join()

        return acronym
    }

    static void main(String[] args) {



        // Prompt user for input
        println "Enter a phrase to abbreviate:"
        def userInput = System.in.newReader().readLine()

        // Generate and print the acronym
        def result = abbreviate(userInput)
        println "The acronym for '${userInput}' is: ${result}"

        // Close the scanner

    }
}
