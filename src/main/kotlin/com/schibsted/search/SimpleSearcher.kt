package com.schibsted.search

import com.schibsted.words.*

class SimpleSearcher(
        private val wordsScoring: WordsScoring,
        private val wordsRegistryMap: Map<String, WordsRegistry>
) : Searcher {

    override fun search(words: List<String>): Map<String, Double> {
        val wordsMap = wordsRegistryMap
                .mapValues { (_, registry) -> words.map { registry.get(it) } }
        if (wordsMap.all { it.value.all { word -> word.count == 0L } }) {
            return emptyMap()
        }
        return wordsMap.mapValues { (_, words) -> wordsScoring.score(words) }
    }

}