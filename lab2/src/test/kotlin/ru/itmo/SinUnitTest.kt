package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import ru.itmo.`fun`.trig.Sin

class SinUnitTest {
    private val accuracy = 1e-5
    private val sin = Sin(accuracy)

    @ParameterizedTest
    @CsvFileSource(resources = ["/sin_unit.csv"])
    fun `sinus tests`(expected: Double, x: Double) {
        assertEquals(expected, sin(x), accuracy)
    }
}