package com.schibsted.search

class DefaultCommandRegistry(
        private val commands: HashMap<String, Command>
) : CommandRegistry {

    constructor() : this(hashMapOf(
            ":quit" to QuitCommand(),
            "" to EmptyCommand()
    ))

    override fun getCommand(name: String): Command? {
        return commands[name]
    }
}