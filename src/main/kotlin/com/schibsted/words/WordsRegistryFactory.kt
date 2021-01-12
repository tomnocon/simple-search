package com.schibsted.words

interface WordsRegistryFactory {
    fun create(): WordsRegistry
}