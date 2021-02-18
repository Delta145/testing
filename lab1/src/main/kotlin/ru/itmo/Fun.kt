package ru.itmo

object Fun {
    fun cos(x: Double): Double {
        return 1 - (x * x / 2) + (x * x * x * x / (4 * 3 * 2) ) - (x * x * x * x * x * x / (6 * 5 * 4 * 3 * 2) ) + (x * x * x * x * x * x * x * x / (8 * 7 * 6 * 5 * 4 * 3 * 2) )
    }
}