package com.schibsted.words

class WordNormalizer {

    fun normalize(word: String) : String? {
        val normalizedWord = word
                .toLowerCase()
                .trim()
                .replace(Regex("[^a-z ]"), "")
        return if (normalizedWord.isNotBlank()) normalizedWord else null
    }
}