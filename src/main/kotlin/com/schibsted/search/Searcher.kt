package com.schibsted.search

interface Searcher {
    fun search(words: List<String>) : Map<String, Double>
}