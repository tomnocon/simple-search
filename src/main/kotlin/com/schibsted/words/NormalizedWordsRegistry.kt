package com.schibsted.words

class NormalizedWordsRegistry(private val wordsRegistry: WordsRegistry) : WordsRegistry {

    private val wordNormalizer = WordNormalizer()

    override fun add(word: String) {
        wordNormalizer.normalize(word)?.let { wordsRegistry.add(it) }
    }

    override fun get(word: String): Word {
        return wordNormalizer.normalize(word)?.let { wordsRegistry.get(it) } ?: Word()
    }
}