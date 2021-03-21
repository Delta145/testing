package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.itmo.`fun`.system.SystemFun
import kotlin.math.PI
import kotlin.test.assertFailsWith

class FullIntegration {
    private val accuracy = 1e-5
    private val systemFun = SystemFun(accuracy)

    @Test
    fun `system test`() {
        assertEquals(-1000050.001416697, systemFun(-0.01), accuracy)
        assertEquals(-8.0, systemFun(-PI / 6), accuracy)
        assertEquals(-1.0, systemFun(-PI / 2), accuracy)
        assertEquals(-73.021, systemFun(-2.9), accuracy)
        assertEquals(23.16773, systemFun(-3.5), accuracy)
        assertEquals(1.0, systemFun(-3 * PI / 2), accuracy)
        assertEquals(165.43333, systemFun(-6.1), accuracy)

        assertEquals(50.8628678169, systemFun(0.01), accuracy)
        assertEquals(13.91067980077366544, systemFun(0.1), accuracy)
        assertEquals(0.0000401395, systemFun(0.74129), accuracy)
        assertEquals(-0.867388, systemFun(1.4), accuracy)
        assertEquals(-0.60548743985918544104309, systemFun(2.0), accuracy)
        assertEquals(0.000058, systemFun(2.669), accuracy)
        assertEquals(0.889608, systemFun(3.5), accuracy)
        assertEquals(7.6765352163453698, systemFun(10.5), accuracy)
        assertEquals(37.66513949162945594, systemFun(100.5), accuracy)

        assertFailsWith<IllegalArgumentException> {
            systemFun(0.0)
        }

        assertFailsWith<IllegalArgumentException> {
            systemFun(-PI)
        }

        assertFailsWith<IllegalArgumentException> {
            systemFun(-10 * PI)
        }
    }

}