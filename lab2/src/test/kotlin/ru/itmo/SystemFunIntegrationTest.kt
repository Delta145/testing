package ru.itmo

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.itmo.`fun`.system.FirstFun
import ru.itmo.`fun`.system.SecondFun
import ru.itmo.`fun`.system.SystemFun
import kotlin.math.PI
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class SystemFunIntegrationTest {
    companion object {
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

            Mockito.`when`(secondFun(0.01)).thenReturn(50.8629)
            Mockito.`when`(secondFun(0.1)).thenReturn(13.9107)
            Mockito.`when`(secondFun(0.1)).thenReturn(13.9107)
            Mockito.`when`(secondFun(0.74129)).thenReturn(0.00004)
            Mockito.`when`(secondFun(1.4)).thenReturn(-0.867388)
            Mockito.`when`(secondFun(2.0)).thenReturn(-0.60548)
            Mockito.`when`(secondFun(2.669)).thenReturn(0.000058)
            Mockito.`when`(secondFun(3.5)).thenReturn(0.889)
            Mockito.`when`(secondFun(10.5)).thenReturn(7.67654)
            Mockito.`when`(secondFun(10.5)).thenReturn(7.67654)
            Mockito.`when`(secondFun(100.5)).thenReturn(37.6651)

            systemFun = SystemFun(1e-10, firstFun, secondFun)
        }
    }

    @Test
    fun test_left() {
        assertEquals(-1005.01, systemFun(-0.1))
        assertEquals(-8.0, systemFun(-PI/6))
        assertEquals(-1.0, systemFun(-PI/2))
        assertEquals(-73.021, systemFun(-2.9))
        assertEquals(23.1677, systemFun(-3.5))
        assertEquals(1.0, systemFun(-3*PI/2))
        assertEquals(165.433, systemFun(-6.1))

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
        assertEquals(50.8629, systemFun(0.01))
        assertEquals(13.9107, systemFun(0.1))
        assertEquals(13.9107, systemFun(0.1))
        assertEquals(0.00004, systemFun(0.74129))
        assertEquals(-0.867388, systemFun(1.4))
        assertEquals(-0.60548, systemFun(2.0))
        assertEquals(0.000058, systemFun(2.669))
        assertEquals(0.889, systemFun(3.5))
        assertEquals(7.67654, systemFun(10.5))
        assertEquals(7.67654, systemFun(10.5))
        assertEquals(37.6651, systemFun(100.5))
    }
}