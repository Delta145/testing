package ru.itmo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.itmo.domain_new.*

class DomainTest {

    @Test
    fun `Ford cannot take another thing if he is already holding something`() {
        val bottleWithFish = AlienThing("стеклянный флакончик,", "в котором плавала, переливаясь, маленькая желтая рыбка.")
        val ford = BetelgeuseMan("Форд")
        ford.takeThing(bottleWithFish)
        val emptyBottle = AlienThing("Пустой флакончик", "")
        val takeThing = ford.takeThing(emptyBottle)
        assertFalse(takeThing.isSuccess)
    }

    @Test
    fun `Arthur is confident if looked at cornflakes`() {
        val arthur = Human("Артур")
        assertFalse(arthur.isConfident)
        val lookActionResult = arthur.lookAt(EarthThing("Пакет кукурзных хлопьев"))
        assertTrue(lookActionResult.isSuccess)
        assertTrue(arthur.isConfident)
    }

    @Test
    fun `Arthur is confident if environment contains cornflakes`() {
        val arthur = Human("Артур")
        assertFalse(arthur.isConfident)
        val bottleWithFish = AlienThing("стеклянный флакончик,", "в котором плавала, переливаясь, маленькая желтая рыбка.")
        val ford = BetelgeuseMan("Форд")
        ford.takeThing(bottleWithFish)
        val dentrassi = AlienThing("нижнее бельё", "дентрасси")
        val matrix = AlienThing("скворншельскими матрицами", "")

        val env = Environment(dentrassi, matrix, ford, EarthThing("Пакет кукурзных хлопьев"))
        arthur.analyzeEnv(env)
        assertTrue(arthur.isConfident)
    }

    @Test
    fun `check and print main plot`() {
        val bottleWithFish = AlienThing("стеклянный флакончик,", "в котором плавала, переливаясь, маленькая желтая рыбка.")
        val ford = BetelgeuseMan("Форд")
        val takeActionResult = ford.takeThing(bottleWithFish)
        assertTrue(takeActionResult.isSuccess)
        assertEquals(ford.holdingThing, bottleWithFish)
        println(takeActionResult.resultDescription)

        val arthur = Human("Артур", true)
        assertTrue(arthur.isConfident)
        val lookActionResult = arthur.lookAt(ford)
        assertTrue(lookActionResult.isSuccess)
        assertFalse(arthur.isConfident)
        println(lookActionResult.resultDescription)
        println(arthur.getStateDescription())

        val dentrassi = AlienThing("нижнее бельё", "дентрасси")
        val matrix = AlienThing("скворншельскими матрицами", "")

        val env = Environment(dentrassi, matrix, ford, null)
        val analyzeResult = arthur.analyzeEnv(env)
        assertFalse(arthur.isConfident)
        println(analyzeResult)
    }

}