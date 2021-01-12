package com.schibsted.words

class InMemoryWordsRegistryFactory : WordsRegistryFactory {
    override fun create(): WordsRegistry {
        return InMemoryWordsRegistry()
    }
}