package com.schibsted.search

import com.schibsted.io.Directory
import com.schibsted.io.InputReader
import com.schibsted.io.OutputWriter
import com.schibsted.io.console.ConsoleReader
import com.schibsted.io.console.ConsoleWriter
import com.schibsted.io.writeLine
import kotlin.math.floor

class SearchLoop(
        private val inputReader: InputReader = ConsoleReader(),
        private val outputWriter: OutputWriter = ConsoleWriter(),
        private val commandRegistry: CommandRegistry = DefaultCommandRegistry(),
        private val searcherFactory: SearcherFactory = SimpleSearcherFactory()
        ) {

    fun run(directory: Directory) {
        val files = directory.getFiles()
        val searcher = searcherFactory.create(files)
        outputWriter.writeLine("${files.size} files read in directory ${directory.path}")
        while (true) {
            outputWriter.write("search> ")
            val input = inputReader.read()
            val command = commandRegistry.getCommand(input)
            if (command == null) {
                val words = input.split(" ")
                val scoreMap = searcher.search(words)
                if (scoreMap.isEmpty()) {
                    outputWriter.writeLine("no matches found")
                } else {
                    scoreMap.toList()
                            .sortedByDescending { (_, score) -> score }
                            .take(10)
                            .forEach { (file, score) -> outputWriter.writeLine("$file : ${"%.0f".format(floor(score * 100))}%") }
                }
            } else {
                command.execute()
            }
        }
    }
}

