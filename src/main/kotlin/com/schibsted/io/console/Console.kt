package com.schibsted.io.console

import com.schibsted.io.InputReader
import com.schibsted.io.OutputWriter

class ConsoleReader : InputReader {
    override fun read(): String {
        return readLine().orEmpty()
    }
}

class ConsoleWriter : OutputWriter {
    override fun write(content: String) {
        print(content)
    }
}