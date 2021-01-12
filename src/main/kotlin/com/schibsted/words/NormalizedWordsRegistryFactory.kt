package com.schibsted.words

class NormalizedWordsRegistryFactory(private val wordsRegistryFactory: WordsRegistryFactory) : WordsRegistryFactory {

    constructor() : this(InMemoryWordsRegistryFactory())

    override fun create(): WordsRegistry {
        return NormalizedWordsRegistry(wordsRegistryFactory.create())
    }
}