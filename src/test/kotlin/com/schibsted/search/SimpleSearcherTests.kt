package com.schibsted.search

import com.schibsted.words.InMemoryWordsRegistry
import com.schibsted.words.WordsRegistry
import com.schibsted.words.WordsScoring
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class SimpleSearcherTests : StringSpec({

    val wordsScoring = mockk<WordsScoring>()
    every { wordsScoring.score(any()) } returns 0.5

    "should score searched words" {
        // Arrange
        val wordsRegistryMap = mapOf(
                "file1" to wordsRegistryOf("word1", "word2"),
                "file2" to wordsRegistryOf("word")
        )
        val searcher : Searcher = SimpleSearcher(
                wordsScoring,
                wordsRegistryMap
        )

        // Act
        val scoreMap = searcher.search(listOf("word1", "word2"))

        // Assert
        scoreMap["file1"] shouldBe 0.5
        scoreMap["file2"] shouldBe 0.5
    }

    "should return empty map when no matches found" {
        // Arrange
        val wordsRegistryMap = mapOf(
                "file1" to wordsRegistryOf("word")
        )
        val searcher : Searcher = SimpleSearcher(
                wordsScoring,
                wordsRegistryMap
        )

        // Act
        val scoreMap = searcher.search(listOf("word1"))

        // Assert
        scoreMap.isEmpty() shouldBe true
    }
})

fun wordsRegistryOf(vararg elements: String) : WordsRegistry {
    val wordsRegistry = InMemoryWordsRegistry()
    elements.forEach { wordsRegistry.add(it) }
    return wordsRegistry
}