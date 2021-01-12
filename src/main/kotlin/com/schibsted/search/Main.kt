package com.schibsted.search

import com.schibsted.cmd.CommandOption
import com.schibsted.cmd.PositionalParser
import com.schibsted.io.system.SystemDirectory
import java.lang.IllegalStateException

const val PATH_ARGUMENT_KEY = "path"

fun main(args: Array<String>) {
    val options = arrayListOf(
            CommandOption(PATH_ARGUMENT_KEY, "Directory of text files to search")
    )
    val parser = PositionalParser(options)
    val commandLine = parser.parse(args);
    val path = commandLine.arguments.get(PATH_ARGUMENT_KEY) ?: throw IllegalStateException("Argument not found")
    SearchLoop().run(SystemDirectory(path))
}