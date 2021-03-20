package ru.itmo.`fun`.log

import ru.itmo.`fun`.Fun
import kotlin.math.pow

open class Ln(accuracy: Double) : Fun(accuracy) {
    override fun invoke(x: Double): Double {
        if (accuracy <= 0.0 || x < 0.0)
            throw IllegalArgumentException("Accuracy should be positive")
        if (x == 0.0)
            return Double.NEGATIVE_INFINITY
        val tailor = { n: Int ->
            (-1.0).pow(n + 1) * (x - 1).pow(n) / n
        }
        return 0.0
    }
}