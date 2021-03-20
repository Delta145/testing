package ru.itmo.`fun`.log

import ru.itmo.`fun`.Fun

open class Log2(
    accuracy: Double,
    private val ln: Ln = Ln(accuracy),
) : Fun(accuracy) {
    private val ln2 = ln(2.0)
    override fun invoke(x: Double): Double = ln(x) / ln2
}