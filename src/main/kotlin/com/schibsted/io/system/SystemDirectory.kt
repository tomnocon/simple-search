package com.schibsted.io.system

import com.schibsted.io.Directory
import com.schibsted.io.File
import java.io.File as JavaFile

class SystemDirectory(override val path: String) : Directory {
    private val file: JavaFile = JavaFile(path)

    init {
        if (file.isFile) {
            throw IllegalArgumentException("Path cannot be a file")
        }
    }

    override fun getFiles(): List<File> {
        return file.walk().filter { it.isFile }.map { SystemFile(it.path) }.toList()
    }
}