package com.schibsted.cmd

class PositionalParser(private val options: List<CommandOption>) : CommandLineParser {

    override fun parse(args: Array<String>): CommandLine {
        if(args.size != options.size){
            throw IllegalArgumentException("Incorrect number of parameters. Required: ${options.size}")
        }
        val argumentsMap = HashMap<String, String>()
        options.zip(args).forEach{
            argumentsMap[it.first.key] = it.second
        }
        return CommandLine(CommandArguments(argumentsMap))
    }
}