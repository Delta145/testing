package ru.itmo.`fun`.trig

import ru.itmo.`fun`.Fun
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow

open class Sin(accuracy: Double) : Fun(accuracy) {

    private fun fact(num: Int): Long =
        if (num > 1) fact(num - 1) * num else 1

    private fun shortenRange(x: Double): Double {
        return if (x > PI || x < -PI) {
            val k = x % (2 * PI)
            when {
                k < -PI -> k + 2 * PI
                k > PI -> k - 2 * PI
                else -> k
            }
        } else
            x
    }

    override fun invoke(input: Double): Double {
        val x = shortenRange(input)
        if (accuracy <= 0)
            throw IllegalArgumentException("Accuracy should be positive")
        val tailor = { n: Int ->
            ((-1.0).pow(n) * x.pow(2 * n + 1)) / fact(2 * n + 1) }
        var result = 0.0
        var current = 10.0
        var prev = 0.0
        var n = 0
        while (abs(prev - current) >= accuracy) {
            prev = current
            current = tailor(n)
            result += current
            n++
        }
        return result
    }
}