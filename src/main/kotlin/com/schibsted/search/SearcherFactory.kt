package com.schibsted.search

import com.schibsted.io.File

interface SearcherFactory  {
    fun create(files: List<File>): Searcher
}