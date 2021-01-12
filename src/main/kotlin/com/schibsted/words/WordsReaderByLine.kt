package com.schibsted.words

import com.schibsted.io.File
import java.io.BufferedReader
import java.io.InputStreamReader

class WordsReaderByLine(
        private val wordsRegistryFactory: WordsRegistryFactory
) : WordsReader {

    override fun read(file: File): WordsRegistry {
        val wordsRegistry = wordsRegistryFactory.create()
        file.read().use {
            val reader = BufferedReader(InputStreamReader(it))
            while (reader.ready()) {
                reader.readLine()
                        .split(" ")
                        .forEach { t -> wordsRegistry.add(t) }
            }
        }
        return wordsRegistry
    }
}