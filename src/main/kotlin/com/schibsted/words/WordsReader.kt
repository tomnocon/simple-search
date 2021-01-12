package com.schibsted.words

import com.schibsted.io.File

interface WordsReader {
    fun read(file: File): WordsRegistry
}