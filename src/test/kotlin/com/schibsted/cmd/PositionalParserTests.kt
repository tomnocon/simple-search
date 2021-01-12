package com.schibsted.cmd

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class PositionalParserTests : StringSpec({

    val parser : CommandLineParser = PositionalParser(
            listOf(
                    CommandOption("opt1", "Argument 1"),
                    CommandOption("opt2", "Argument 2"),
            )
    )

    "should parse command line arguments" {
        val cmd = parser.parse(arrayOf("arg1", "arg2"))
        cmd.arguments.get("opt1") shouldBe "arg1"
        cmd.arguments.get("opt2") shouldBe "arg2"
    }

    "should reject invalid number of arguments" {
        shouldThrow<IllegalArgumentException> {
            parser.parse(arrayOf("arg1"))
        }
        shouldThrow<IllegalArgumentException> {
            parser.parse(arrayOf("arg1", "arg2", "arg3"))
        }
    }
})