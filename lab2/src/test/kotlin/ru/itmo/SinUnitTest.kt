package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.itmo.`fun`.trig.Sin
import kotlin.math.PI

class SinUnitTest {
    private val accuracy = 1e-5

    @Test
    fun `sinus test`() {
        val sin = Sin(accuracy)
        assertEquals(0.0, sin(0.0), accuracy)
        assertEquals(-0.09983341664682815, sin(-0.1), accuracy)
        assertEquals(-0.5, sin(-PI / 6), accuracy)
        assertEquals(-1.0, sin(-PI / 2), accuracy)
        assertEquals(-0.23924932921398243, sin(-2.9), accuracy)
        assertEquals(0.0, sin(-PI), 1e-5)
        assertEquals(0.35078322768961984, sin(-3.5), accuracy)
        assertEquals(1.0, sin(-3 * PI / 2), accuracy)
        assertEquals(0.1821625042720955400, sin(-6.1), accuracy)
        assertEquals(0.0, sin(-10 * PI), accuracy)
    }
}