package com.schibsted.words

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe

class LogarithmicWordsScoringTests : StringSpec({
    val wordsScoring : WordsScoring = LogarithmicWordsScoring()

    "should score 0 if none of the words match" {
        wordsScoring.score(listOf(Word(0), Word(0))) shouldBe 0.0
    }

    "should score 1 if all the words match" {
        wordsScoring.score(listOf(Word(1), Word(1))) shouldBe 1.0
    }

    "should score between 0 and 1 if it contains only some of the words" {
        val score = wordsScoring.score(listOf(Word(99999999999999), Word(0)))
        score shouldBeGreaterThan 0.0
        score shouldBeLessThan 1.0
    }
})