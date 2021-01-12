package com.schibsted.search

interface CommandRegistry {
    fun getCommand(name: String) : Command?
}