package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.itmo.`fun`.system.FirstFun
import ru.itmo.`fun`.system.SecondFun
import ru.itmo.`fun`.system.SystemFun
import kotlin.math.PI
import kotlin.test.assertFailsWith


class SystemFunIntegrationTest {
    companion object {
        private const val accuracy = 1e-5
        private lateinit var systemFun: SystemFun
        @BeforeAll
        @JvmStatic
        fun setup() {
            val firstFun = Mockito.mock(FirstFun::class.java)
            val secondFun = Mockito.mock(SecondFun::class.java)
            Mockito.`when`(firstFun(0.0)).thenThrow(IllegalArgumentException(""))
            Mockito.`when`(firstFun(-0.1)).thenReturn(-1005.01)
            Mockito.`when`(firstFun(-PI / 6)).thenReturn(-8.0)
            Mockito.`when`(firstFun(-PI / 2)).thenReturn(-1.0)
            Mockito.`when`(firstFun(-2.9)).thenReturn(-73.021)
            Mockito.`when`(firstFun(-PI)).thenThrow(IllegalArgumentException(""))
            Mockito.`when`(firstFun(-3.5)).thenReturn(23.1677)
            Mockito.`when`(firstFun(-3 * PI / 2)).thenReturn(1.0)
            Mockito.`when`(firstFun(-6.1)).thenReturn(165.433)
            Mockito.`when`(firstFun(-10 * PI)).thenThrow(IllegalArgumentException(""))

            Mockito.`when`(secondFun(0.01)).thenReturn(50.8628678169)
            Mockito.`when`(secondFun(0.1)).thenReturn(13.91067980077366544)
            Mockito.`when`(secondFun(0.74129)).thenReturn(0.0000401395)
            Mockito.`when`(secondFun(1.4)).thenReturn(-0.867388)
            Mockito.`when`(secondFun(2.0)).thenReturn(-0.60548743985918544104309)
            Mockito.`when`(secondFun(2.669)).thenReturn(0.000058)
            Mockito.`when`(secondFun(3.5)).thenReturn(0.889608)
            Mockito.`when`(secondFun(10.5)).thenReturn(7.6765352163453698)
            Mockito.`when`(secondFun(100.5)).thenReturn(37.66513949162945594)

            systemFun = SystemFun(accuracy, firstFun, secondFun)
        }
    }

    @Test
    fun test_left() {
        assertEquals(-1005.01, systemFun(-0.1), accuracy)
        assertEquals(-8.0, systemFun(-PI/6), accuracy)
        assertEquals(-1.0, systemFun(-PI/2), accuracy)
        assertEquals(-73.021, systemFun(-2.9), accuracy)
        assertEquals(23.1677, systemFun(-3.5), accuracy)
        assertEquals(1.0, systemFun(-3*PI/2), accuracy)
        assertEquals(165.433, systemFun(-6.1), accuracy)

        assertFailsWith<IllegalArgumentException> {
            systemFun(0.0)
        }
        assertFailsWith<IllegalArgumentException> {
            systemFun(-PI)
        }
        assertFailsWith<IllegalArgumentException> {
            systemFun(-10*PI)
        }
    }

    @Test
    fun test_right() {
        assertEquals(50.8628678169, systemFun(0.01), accuracy)
        assertEquals(13.91067980077366544, systemFun(0.1), accuracy)
        assertEquals(0.0000401395, systemFun(0.74129), accuracy)
        assertEquals(-0.867388, systemFun(1.4), accuracy)
        assertEquals(-0.60548743985918544104309, systemFun(2.0), accuracy)
        assertEquals(0.000058, systemFun(2.669), accuracy)
        assertEquals(0.889608, systemFun(3.5), accuracy)
        assertEquals(7.6765352163453698, systemFun(10.5), accuracy)
        assertEquals(37.66513949162945594, systemFun(100.5), accuracy)
    }
}