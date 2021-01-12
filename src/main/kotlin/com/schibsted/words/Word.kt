package com.schibsted.words

data class Word(
        val count: Long = 0
)

fun Word.increment() : Word {
    return Word(count + 1)
}

