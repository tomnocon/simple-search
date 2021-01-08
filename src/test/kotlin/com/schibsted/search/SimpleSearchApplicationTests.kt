package com.schibsted.search

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.*

class SimpleSearchApplicationTests : StringSpec({
    "length should return size of string" {
        "hello".length shouldBe 5
    }
})