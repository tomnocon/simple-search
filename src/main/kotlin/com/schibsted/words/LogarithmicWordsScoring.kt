package com.schibsted.words

class LogarithmicWordsScoring : WordsScoring {
    override fun score(words: List<Word>): Double {
        val noneWordMatch = words.all { it.count == 0L }
        if (noneWordMatch) {
            return 0.0
        }
        val allWordsMatch = words.all { it.count > 0 }
        if (allWordsMatch) {
            return 1.0
        }
        val wordsMatchSum = words.map { it.count }.sum()
        return 1.0 - 1.0 / (1 + wordsMatchSum)
    }
}