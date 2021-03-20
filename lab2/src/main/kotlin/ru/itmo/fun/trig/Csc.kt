package ru.itmo.`fun`.trig

import ru.itmo.`fun`.Fun
import kotlin.math.PI

open class Csc(
    accuracy: Double,
    private val sin: Sin = Sin(accuracy),
) : Fun(accuracy) {
    override fun invoke(x: Double): Double {
        if (x % PI == 0.0)
            throw IllegalArgumentException("Division by zero")
        return 1.0 / sin(x)
    }
}