package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import ru.itmo.`fun`.log.Ln

class LnUnitTest {
    private val accuracy = 1e-5
    private val ln = Ln(accuracy)

    @Test
    fun `ln at 0 should return negative inf`() {
        assertEquals(Double.NEGATIVE_INFINITY, ln(0.0), accuracy)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/ln_unit.csv"])
    fun `ln param test`(expected: Double, x: Double) {
        assertEquals(expected, ln(x), accuracy)
    }
}