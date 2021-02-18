package ru.itmo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.itmo.domain.*

class DomainTest {
    @Test
    fun `check Arthur state is anxious if Ford suggested to put in a ear the bottle with a small fish`() {
        val fish = BetelgeuseFish("yellow", FishSize.SMALL)
        val bottle = BetelgeuseGlassBottle(fish)
        val ford = BetelgeuseMan("Ford", bottle, 0.0)
        val arthur = Human("Arthur", 0.0)
        ford.suggestPutInEar(arthur)
        assertEquals(HumanState.ANXIOUS, arthur.state)
    }

    @Test
    fun `check Arthur state is confident if he looked at CornFlakes(weight=500) after Ford suggestion`() {
        val fish = BetelgeuseFish("yellow", FishSize.SMALL)
        val bottle = BetelgeuseGlassBottle(fish)
        val ford = BetelgeuseMan("Ford", bottle, 0.0)
        val arthur = Human("Arthur", 0.0)
        ford.suggestPutInEar(arthur)
        val cornFlakes = CornFlakes(500.0)
        arthur.lookAt(cornFlakes)
        assertEquals(HumanState.CONFIDENT, arthur.state)
    }

    @Test
    fun `check Arthur state is MADNESS if Ford suggested to put in a ear the bottle with a LAARGE fish`() {
        val fish = BetelgeuseFish("yellow", FishSize.LARGE)
        val bottle = BetelgeuseGlassBottle(fish)
        val ford = BetelgeuseMan("Ford", bottle, 0.0)
        val arthur = Human("Arthur", 0.0)
        ford.suggestPutInEar(arthur)
        assertEquals(HumanState.MADNESS, arthur.state)
    }

    @Test
    fun `check betelgeuse man stress resistance after looking at 10 humans`() {
        val fish = BetelgeuseFish("yellow", FishSize.LARGE)
        val bottle = BetelgeuseGlassBottle(fish)
        val ford = BetelgeuseMan("Ford", bottle, 0.0)
        val humans = (1..10).asIterable().map { i ->
            Human("human $i", 0.0)
        }
        humans.forEach { h -> ford.lookAt(h) }
        assertEquals(BetelgeuseManState.CONFIDENT, ford.state)
    }
}