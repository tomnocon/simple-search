package com.schibsted.search

import com.schibsted.io.Directory
import com.schibsted.io.File
import com.schibsted.io.InputReader
import com.schibsted.io.OutputWriter
import com.schibsted.words.WordsReader
import com.schibsted.words.WordsScoring
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.beOneOf
import io.mockk.*
import java.lang.RuntimeException

class SearchLoopIT : StringSpec({

    val directory = mockk<Directory>()
    val inputReader = mockk<InputReader>()
    val outputWriter = mockk<OutputWriter>()
    val searcherFactory = mockk<SearcherFactory>()
    val searcher = mockk<Searcher>()

    every { directory.getFiles() } returns emptyList()
    every { directory.path } returns "/foo/bar"
    every { searcherFactory.create(any()) } returns searcher

    val searchLoop = SearchLoop(
            inputReader = inputReader,
            outputWriter = outputWriter,
            searcherFactory = searcherFactory
    )

    "should write 'no matches found' when no matches" {
        // Arrange
        readerReturns(inputReader,"word1 word2")
        every { searcher.search(any()) } returns emptyMap()
        every { outputWriter.write(any()) } just Runs

        try {
            // Act
            searchLoop.run(directory)
        } catch (ex: Exception) {
        }

        // Assert
        verify {
            searcher.search(eq(listOf("word1", "word2")))
        }
        verifySequence {
            outputWriter.write("0 files read in directory /foo/bar\n")
            outputWriter.write("search> ")
            outputWriter.write("no matches found\n")
            outputWriter.write("search> ")
        }
    }

    "should print top 10 matches in descending order" {
        // Arrange
        readerReturns(inputReader)
        every { searcher.search(any()) } returns mapOf(
                "file7" to 0.7777,
                "file2" to 0.222,
                "file3" to 0.3333,
                "file1" to 0.1111,
                "file10" to 1.0,
                "file4" to 0.444,
                "file5" to 0.555,
                "file6" to 0.666,
                "file0" to 0.000,
                "file8" to 0.888,
                "file9" to 0.999
        )
        every { outputWriter.write(any()) } just Runs

        try {
            // Act
            searchLoop.run(directory)
        } catch (ex: Exception) {
        }

        // Assert
        verifySequence {
            outputWriter.write("0 files read in directory /foo/bar\n")
            outputWriter.write("search> ")
            outputWriter.write("file10 : 100%\n")
            outputWriter.write("file9 : 99%\n")
            outputWriter.write("file8 : 88%\n")
            outputWriter.write("file7 : 77%\n")
            outputWriter.write("file6 : 66%\n")
            outputWriter.write("file5 : 55%\n")
            outputWriter.write("file4 : 44%\n")
            outputWriter.write("file3 : 33%\n")
            outputWriter.write("file2 : 22%\n")
            outputWriter.write("file1 : 11%\n")
            outputWriter.write("search> ")
        }
    }

    afterTest {
        clearMocks(outputWriter)
    }

})

fun readerReturns(reader: InputReader, input: String = "word"){
    var counter = 0;
    every { reader.read() } answers {
        if (counter > 0) {
            throw Exception()
        }
        counter++
        input
    }
}