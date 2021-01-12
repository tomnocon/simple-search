package com.schibsted.words

interface WordsRegistry {
    fun add(word: String)
    fun get(word: String) : Word
}