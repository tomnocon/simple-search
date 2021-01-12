package com.schibsted.cmd

interface CommandLineParser {
    fun parse(args: Array<String>) : CommandLine
}