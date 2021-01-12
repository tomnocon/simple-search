package com.schibsted.words

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WordNormalizerTests : StringSpec({
    val wordNormalizer = WordNormalizer()

    "should skip empty strings" {
        wordNormalizer.normalize(" ") shouldBe null
    }

    "should filter white spaces" {
        wordNormalizer.normalize(" word ") shouldBe "word"
    }

    "should lowercase letters" {
        wordNormalizer.normalize("WorD") shouldBe "word"
    }

    "should filter non letters " {
        wordNormalizer.normalize("~?Wo0Rd)&!") shouldBe "word"
    }
})