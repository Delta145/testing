package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.itmo.`fun`.system.FirstFun
import ru.itmo.`fun`.trig.Csc
import kotlin.math.PI
import kotlin.test.assertFailsWith

class FirstFunIntegrationTest {
    companion object {
        private const val accuracy = 1e-5
        private lateinit var firstFun: FirstFun
        @BeforeAll
        @JvmStatic
        fun setup() {
            val csc = Mockito.mock(Csc::class.java)
            Mockito.`when`(csc(0.0)).thenThrow(IllegalArgumentException(""))
            Mockito.`when`(csc(-0.01)).thenReturn(-100.001666686111316)
            Mockito.`when`(csc(-0.1)).thenReturn(-10.016686131634776)
            Mockito.`when`(csc(-PI / 6)).thenReturn(-2.0)
            Mockito.`when`(csc(-PI / 2)).thenReturn(-1.0)
            Mockito.`when`(csc(-2.9)).thenReturn(-4.1797400364103385)
            Mockito.`when`(csc(-PI)).thenThrow(IllegalArgumentException(""))
            Mockito.`when`(csc(-3.5)).thenReturn(2.850763437540464)
            Mockito.`when`(csc(-3 * PI / 2)).thenReturn(1.0)
            Mockito.`when`(csc(-6.1)).thenReturn(5.48960)
            Mockito.`when`(csc(-10 * PI)).thenThrow(IllegalArgumentException(""))

            firstFun = FirstFun(accuracy, csc)
        }
    }

    @Test
    fun test_left() {
        assertEquals(-1000050.001416697, firstFun(-0.01), accuracy)
        assertEquals(-1005.0141969, firstFun(-0.1), accuracy)
        assertEquals(-8.0, firstFun(-PI /6), accuracy)
        assertEquals(-1.0, firstFun(-PI /2), accuracy)
        assertEquals(-73.021, firstFun(-2.9), accuracy)
        assertEquals(23.167733, firstFun(-3.5), accuracy)
        assertEquals(1.0, firstFun(-3* PI /2), accuracy)
        assertEquals(165.43298, firstFun(-6.1), accuracy)

        assertFailsWith<IllegalArgumentException> {
            firstFun(0.0)
        }
        assertFailsWith<IllegalArgumentException> {
            firstFun(-PI)
        }
        assertFailsWith<IllegalArgumentException> {
            firstFun(-10* PI)
        }
    }
}