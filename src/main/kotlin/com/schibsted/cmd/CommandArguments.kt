package com.schibsted.cmd

class CommandArguments(private val argumentsMap: Map<String, String>) {

    fun get(key: String) : String? {
        return argumentsMap[key]
    }
}
