package com.schibsted.io

import java.io.InputStream

interface File {
    val path: String
    val name: String
    fun read() : InputStream
}