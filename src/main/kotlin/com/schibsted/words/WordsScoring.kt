package com.schibsted.words

interface WordsScoring {
    fun score(words: List<Word>) : Double
}