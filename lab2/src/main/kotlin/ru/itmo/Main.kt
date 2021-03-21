package ru.itmo

import ru.itmo.`fun`.trig.Sin
import ru.itmo.util.CSVWriter
import kotlin.math.PI

fun main() {
    val sysFun = Sin(1e-5)
    val writer = CSVWriter("C:\\test\\out.csv", sysFun)
    writer.writeComputations(-2 * PI, 2 * PI, 0.05)
}