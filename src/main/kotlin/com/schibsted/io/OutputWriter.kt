package com.schibsted.io

interface OutputWriter {
    fun write(content: String)
}

fun OutputWriter.writeLine(content: String) {
    write(content + System.lineSeparator())
}