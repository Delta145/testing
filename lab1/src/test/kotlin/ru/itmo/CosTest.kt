package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class CosTest {

    @Test
    fun `test 0 value`(){
        assertEquals(1.0, Fun.cos(0.0), Fun.PRECISION)
    }

    @Test
    fun `test PI value`(){
        assertEquals(-1.0, Fun.cos(Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test -PI value`(){
        assertEquals(-1.0, Fun.cos(-Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test 0,5PI value`(){
        assertEquals(0.0, Fun.cos(0.5 * Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test -0,5PI value`(){
        assertEquals(0.0, Fun.cos(-0.5 * Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test 0,33333PI value`(){
        assertEquals(0.5, Fun.cos(0.33333 * Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test -0,33333PI value`(){
        assertEquals(0.5, Fun.cos(-0.33333 * Math.PI), Fun.PRECISION)
    }
    @Test
    fun `test 0,66666666PI value`(){
        assertEquals(-0.5, Fun.cos(0.66666666 * Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test -0,66666666PI value`(){
        assertEquals(-0.5, Fun.cos(-0.66666666 * Math.PI), Fun.PRECISION)
    }

    @Test
    fun `test values more than PI and less than -PI return NaN`(){
        assertEquals(Double.NaN, Fun.cos(-Math.PI - 0.00000001), Fun.PRECISION)
        assertEquals(Double.NaN, Fun.cos(Math.PI + 0.00000001), Fun.PRECISION)
        assertEquals(Double.NaN, Fun.cos(10 * Math.PI), Fun.PRECISION)
        assertEquals(Double.NaN, Fun.cos(-100 * Math.PI), Fun.PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/cos.csv"])
    fun `Cos param test`(expected: Double, x: Double){
        assertEquals(expected, Fun.cos(x), Fun.PRECISION)
    }

}