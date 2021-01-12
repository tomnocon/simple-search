package com.schibsted.search

import kotlin.system.exitProcess

class QuitCommand : Command {
    override fun execute() {
        exitProcess(0)
    }
}