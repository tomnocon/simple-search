package com.schibsted.search

import com.schibsted.io.File
import com.schibsted.words.*

class SimpleSearcherFactory(
        private val wordsReader: WordsReader = WordsReaderByLine(NormalizedWordsRegistryFactory()),
        private val wordsScoring: WordsScoring = LogarithmicWordsScoring()
) : SearcherFactory {

    override fun create(files: List<File>): Searcher {
        val wordsRegistryMap = files.associateBy({ it.name }, { wordsReader.read(it) })
        return SimpleSearcher(wordsScoring, wordsRegistryMap)
    }
}