package com.schibsted.io

interface Directory {
    val path: String
    fun getFiles(): List<File>
}