package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.itmo.`fun`.log.Ln

class LnUnitTest {
    private val accuracy = 1e-5

    @Test
    fun `ln test`() {
        val ln = Ln(accuracy)
        assertEquals(Double.NEGATIVE_INFINITY, ln(0.0), accuracy)
        assertEquals(-4.6051701859880913680359, ln(0.01), accuracy)
        assertEquals(-2.302585092994045684017991454, ln(0.1), accuracy)
        assertEquals(-0.299363367225640879281420, ln(0.74129), accuracy)
        assertEquals(0.33647223662121293050459341021, ln(1.4), accuracy)
        assertEquals(0.693147180559945309417232, ln(2.0), accuracy)
        assertEquals(0.98170387042238708562000549022350, ln(2.669), accuracy)
        assertEquals(1.25276296849536799568812062198500, ln(3.5), accuracy)
        assertEquals(1.6094379124341003746007593, ln(5.0), accuracy)
        assertEquals(2.30258509299404568401799145, ln(10.0), accuracy)
        assertEquals(2.351375257163477687083365858907528866, ln(10.5), accuracy)
        assertEquals(4.61015772749913044164808511182, ln(100.5), accuracy)
    }
}