package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CosTest {

    @Test
    fun `test 0 value`(){
        assertEquals(1.0, Fun.cos(0.0), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test PI value`(){
        assertEquals(-1.0, Fun.cos(Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test -PI value`(){
        assertEquals(-1.0, Fun.cos(-Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test 0,5PI value`(){
        assertEquals(0.0, Fun.cos(0.5 * Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test -0,5PI value`(){
        assertEquals(0.0, Fun.cos(-0.5 * Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test 0,33333PI value`(){
        assertEquals(0.5, Fun.cos(0.33333 * Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test -0,33333PI value`(){
        assertEquals(0.5, Fun.cos(-0.33333 * Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }
    @Test
    fun `test 0,66666666PI value`(){
        assertEquals(-0.5, Fun.cos(0.66666666 * Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

    @Test
    fun `test -0,66666666PI value`(){
        assertEquals(-0.5, Fun.cos(-0.66666666 * Math.PI), Fun.PRECISION, "Правая граница не верна. Тест: asin(1.0)")
    }

}