package ru.itmo.util

import ru.itmo.`fun`.Fun
import java.io.File
import java.io.FileWriter

class CSVWriter(
    filename: String,
    private val `fun`: Fun,
) : AutoCloseable {
    private val file: File = File(filename)
    private var fileWriter: FileWriter

    init {
        if (!file.exists()) {
            if (file.createNewFile()) {
                println("File created")
            } else {
                throw IllegalArgumentException("Impossible to create the file")
            }
        }
        fileWriter = FileWriter(file)
    }

    fun writeComputations(start: Double, end: Double, step: Double) {
        var current = start
        fileWriter.write("x,y\n")
        while (current <= end) {
            var y = Double.NaN
            try {
                y = `fun`(current)
            } catch (e: Exception) {}
            fileWriter.write("$current,$y\n")
            current += step
        }
        fileWriter.flush()
    }

    override fun close() {
        fileWriter.close()
    }
}