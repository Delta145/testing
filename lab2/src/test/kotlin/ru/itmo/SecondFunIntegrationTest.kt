package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import ru.itmo.`fun`.log.Ln
import ru.itmo.`fun`.log.Log10
import ru.itmo.`fun`.log.Log2
import ru.itmo.`fun`.log.Log5
import ru.itmo.`fun`.system.SecondFun

class SecondFunIntegrationTest {
    companion object {
        private lateinit var secondFun: SecondFun
        @BeforeAll
        @JvmStatic
        fun setup() {
            val ln = Mockito.mock(Ln::class.java)
            val log2 = Mockito.mock(Log2::class.java)
            val log5 = Mockito.mock(Log5::class.java)
            val log10 = Mockito.mock(Log10::class.java)

            Mockito.`when`(ln(0.01)).thenReturn(-4.605170185988091)
            Mockito.`when`(ln(0.1)).thenReturn(-2.302585092994)
            Mockito.`when`(ln(0.74129)).thenReturn(-0.2993633672256409)
            Mockito.`when`(ln(1.4)).thenReturn(0.3364722366212129)
            Mockito.`when`(ln(2.0)).thenReturn(0.6931471805599453)
            Mockito.`when`(ln(2.669)).thenReturn(0.981703870422387)
            Mockito.`when`(ln(3.5)).thenReturn(1.252762968495368)
            Mockito.`when`(ln(10.5)).thenReturn(2.351375257163477687083365)
            Mockito.`when`(ln(100.5)).thenReturn(4.61015772749913)

            Mockito.`when`(log2(0.01)).thenReturn(-6.643856189774724)
            Mockito.`when`(log2(0.1)).thenReturn(-3.321928094887362)
            Mockito.`when`(log2(0.74129)).thenReturn(-0.43189004532025377)
            Mockito.`when`(log2(1.4)).thenReturn(0.48542682717024166)
            Mockito.`when`(log2(2.0)).thenReturn(1.0)
            Mockito.`when`(log2(2.669)).thenReturn(1.4162993054798794)
            Mockito.`when`(log2(3.5)).thenReturn(1.8073549220576042)
            Mockito.`when`(log2(10.5)).thenReturn(3.39231742277876028889570826117964731)
            Mockito.`when`(log2(100.5)).thenReturn(6.651051691178928)

            Mockito.`when`(log5(0.01)).thenReturn(-2.861353)
            Mockito.`when`(log5(0.1)).thenReturn(-1.43068)
            Mockito.`when`(log5(0.74129)).thenReturn(-0.186005)
            Mockito.`when`(log5(1.4)).thenReturn(0.209062)
            Mockito.`when`(log5(2.0)).thenReturn(0.430677)
            Mockito.`when`(log5(2.669)).thenReturn(0.609967)
            Mockito.`when`(log5(3.5)).thenReturn(0.778385)
            Mockito.`when`(log5(10.5)).thenReturn(1.46099159153475980122777613605444669207)
            Mockito.`when`(log5(100.5)).thenReturn(2.864452)

            Mockito.`when`(log10(0.01)).thenReturn(-2.0)
            Mockito.`when`(log10(0.1)).thenReturn(-1.0)
            Mockito.`when`(log10(0.74129)).thenReturn(-0.13001185847007263)
            Mockito.`when`(log10(1.4)).thenReturn(0.146128035678238)
            Mockito.`when`(log10(2.0)).thenReturn(0.3010299956639812)
            Mockito.`when`(log10(2.669)).thenReturn(0.4263485737875077)
            Mockito.`when`(log10(3.5)).thenReturn(0.5440680443502757)
            Mockito.`when`(log10(10.5)).thenReturn(1.02118929906993807279350526712325847591551137)
            Mockito.`when`(log10(100.5)).thenReturn(2.002166061756508)

            secondFun = SecondFun(1e-5, ln, log2, log5, log10)
        }
    }

    @Test
    fun test_right() {
        assertEquals(50.86286, secondFun(0.01), 1e-5)
        assertEquals(13.9107, secondFun(0.1), 1e-5)
        assertEquals(0.00004, secondFun(0.74129), 1e-5)
        assertEquals(-0.867388, secondFun(1.4), 1e-5)
        assertEquals(-0.60548, secondFun(2.0), 1e-5)
        assertEquals(0.000058, secondFun(2.669), 1e-5)
        assertEquals(0.889607, secondFun(3.5), 1e-5)
        assertEquals(7.67654, secondFun(10.5), 1e-5)
        assertEquals(37.665139, secondFun(100.5), 1e-5)
    }
}