package com.schibsted.words

class InMemoryWordsRegistry : WordsRegistry {

    private val map = HashMap<String, Word>()

    override fun add(word: String) {
        map[word] = map.getOrDefault(word, Word()).increment()
    }

    override fun get(word: String): Word {
        return map.getOrDefault(word, Word())
    }
}