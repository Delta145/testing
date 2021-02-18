package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CosTest {
    @Test
    fun testRightBorder(){
        assertEquals(0.0, Fun.cos(Math.PI / 2), 0.0001, "Правая граница не верна. Тест: asin(1.0)")
    }
    @Test
    fun testCenter(){
        assertEquals(1.0, Fun.cos(0.0), 0.0001, "Правая граница не верна. Тест: asin(1.0)")
    }
    @Test
    fun testPI(){
        assertEquals(-1.0, Fun.cos(Math.PI), 0.0001, "Правая граница не верна. Тест: asin(1.0)")
    }

}