package ru.itmo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.itmo.`fun`.trig.Csc
import ru.itmo.`fun`.trig.Sin
import kotlin.math.PI
import kotlin.test.assertFailsWith

class TrigonometricIntegrationTest {
    companion object {
        private const val accuracy = 1e-5
        private lateinit var csc: Csc
        @BeforeAll
        @JvmStatic
        fun setup() {
            val sin = Mockito.mock(Sin::class.java)
            Mockito.`when`(sin(0.0)).thenReturn(0.0)
            Mockito.`when`(sin(-0.1)).thenReturn(-0.09983341664682815)
            Mockito.`when`(sin(-PI / 6)).thenReturn(-0.5)
            Mockito.`when`(sin(-PI / 2)).thenReturn(-1.0)
            Mockito.`when`(sin(-2.9)).thenReturn(-0.23924932921398243)
            Mockito.`when`(sin(-PI)).thenReturn(0.0)
            Mockito.`when`(sin(-3.5)).thenReturn(0.35078322768961984)
            Mockito.`when`(sin(-3 * PI / 2)).thenReturn(1.0)
            Mockito.`when`(sin(-6.1)).thenReturn(0.182162504272095540024128363224657015)
            Mockito.`when`(sin(-10 * PI)).thenReturn(0.0)

            csc = Csc(accuracy, sin)
        }
    }

    @Test
    fun test_left() {
        Assertions.assertEquals(-10.016686131634776, csc(-0.1), accuracy)
        Assertions.assertEquals(-2.0, csc(-PI / 6), accuracy)
        Assertions.assertEquals(-1.0, csc(-PI / 2), accuracy)
        Assertions.assertEquals(-4.1797400364103385, csc(-2.9), accuracy)
        Assertions.assertEquals(2.850763437540464, csc(-3.5), accuracy)
        Assertions.assertEquals(1.0, csc(-3 * PI / 2), accuracy)
        Assertions.assertEquals(5.489603, csc(-6.1), accuracy)

        assertFailsWith<IllegalArgumentException> {
            csc(0.0)
        }
        assertFailsWith<IllegalArgumentException> {
            csc(-PI)
        }
        assertFailsWith<IllegalArgumentException> {
            csc(-10 * PI)
        }
    }
}