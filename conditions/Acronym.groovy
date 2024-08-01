package com.conditions

class Acronym {
    static String abbreviate(String phrase) {
        // Remove punctuation except hyphens and underscores
        def cleanedPhrase = phrase.replaceAll(/[^\w\s-]/, '')

        // Split the phrase into words based on spaces, hyphens, and underscores
        def words = cleanedPhrase.split(/[\s-_]+/)

        // Create the acronym by taking the first letter of each word and converting to uppercase
        def acronym = words.collect { it[0].toUpperCase() }.join()

        return acronym
    }

    static void main(String[] args) {
        // Set up scanner to read user input from the console
        Scanner scanner = new Scanner(System.in)

        // Prompt user for input
        println "Enter a phrase to abbreviate:"
        def userInput = scanner.nextLine()

        // Generate and print the acronym
        def result = abbreviate(userInput)
        println "The acronym for '${userInput}' is: ${result}"

        // Close the scanner
        scanner.close()
    }
}
