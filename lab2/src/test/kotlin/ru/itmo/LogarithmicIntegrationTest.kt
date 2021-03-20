package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.itmo.`fun`.log.Ln
import ru.itmo.`fun`.log.Log10
import ru.itmo.`fun`.log.Log2
import ru.itmo.`fun`.log.Log5

class LogarithmicIntegrationTest {

    companion object {
        private lateinit var log_2: Log2
        private lateinit var log_5: Log5
        private lateinit var log_10: Log10
        private const val accuracy = 1e-5

        @BeforeAll
        @JvmStatic
        fun setup() {
            val ln = Mockito.mock(Ln::class.java)

            Mockito.`when`(ln(0.01)).thenReturn(-4.6051701859880913680359)
            Mockito.`when`(ln(0.1)).thenReturn(-2.302585092994045684017991454)
            Mockito.`when`(ln(0.74129)).thenReturn(-0.299363367225640879281420)
            Mockito.`when`(ln(1.4)).thenReturn(0.33647223662121293050459341021)
            Mockito.`when`(ln(2.0)).thenReturn(0.693147180559945309417232)
            Mockito.`when`(ln(5.0)).thenReturn(1.6094379124341003746007593)
            Mockito.`when`(ln(2.669)).thenReturn(0.98170387042238708562000549022350)
            Mockito.`when`(ln(3.5)).thenReturn(1.25276296849536799568812062198500)
            Mockito.`when`(ln(10.0)).thenReturn(2.30258509299404568401799145)
            Mockito.`when`(ln(10.5)).thenReturn(2.351375257163477687083365858907528866)
            Mockito.`when`(ln(100.5)).thenReturn(4.61015772749913044164808511182)

            log_2 = Log2(accuracy, ln)
            log_5 = Log5(accuracy, ln)
            log_10 = Log10(accuracy, ln)
        }
    }

    @Test
    fun `test log_2`() {
        assertEquals(-6.6438561, log_2(0.01), accuracy)
        assertEquals(-3.32193, log_2(0.1), accuracy)
        assertEquals(-0.431890, log_2(0.74129), accuracy)
        assertEquals(0.485427, log_2(1.4), accuracy)
        assertEquals(1.0, log_2(2.0), accuracy)
        assertEquals(1.41630, log_2(2.669), accuracy)
        assertEquals(1.80735, log_2(3.5), accuracy)
        assertEquals(3.39232, log_2(10.5), accuracy)
        assertEquals(6.651052, log_2(100.5), accuracy)
    }
    @Test
    fun `test log_5`() {
        assertEquals(-2.86135, log_5(0.01), accuracy)
        assertEquals(-1.43068, log_5(0.1), accuracy)
        assertEquals(-0.186005, log_5(0.74129), accuracy)
        assertEquals(0.20906, log_5(1.4), accuracy)
        assertEquals(0.430677, log_5(2.0), accuracy)
        assertEquals(0.609967, log_5(2.669), accuracy)
        assertEquals(0.778385, log_5(3.5), accuracy)
        assertEquals(1.46099, log_5(10.5), accuracy)
        assertEquals(2.864452, log_5(100.5), accuracy)
    }

    @Test
    fun `test log_10`() {
        assertEquals(-2.0, log_10(0.01), accuracy)
        assertEquals(-1.0, log_10(0.1), accuracy)
        assertEquals(-0.130012, log_10(0.74129), accuracy)
        assertEquals(0.146128, log_10(1.4), accuracy)
        assertEquals(0.301030, log_10(2.0), accuracy)
        assertEquals(0.426349, log_10(2.669), accuracy)
        assertEquals(0.544068, log_10(3.5), accuracy)
        assertEquals(1.02119, log_10(10.5), accuracy)
        assertEquals(2.002166, log_10(100.5), accuracy)
    }

}