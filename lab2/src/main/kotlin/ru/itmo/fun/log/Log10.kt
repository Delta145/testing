package ru.itmo.`fun`.log

import ru.itmo.`fun`.Fun

open class Log10(
    accuracy: Double,
    private val ln: Ln = Ln(accuracy),
) : Fun(accuracy) {
    private val ln10 = ln(10.0)
    override fun invoke(x: Double): Double = ln(x) / ln10
}