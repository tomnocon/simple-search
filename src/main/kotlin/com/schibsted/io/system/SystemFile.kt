package com.schibsted.io.system

import com.schibsted.io.File
import java.io.InputStream
import java.io.File as JavaFile

class SystemFile(override val path: String) : File {
    private val file: JavaFile = JavaFile(path)

    init {
        if (file.isDirectory) {
            throw IllegalArgumentException("Path cannot be a file")
        }
    }

    override val name: String
        get() = file.name

    override fun read(): InputStream {
        return file.inputStream()
    }
}