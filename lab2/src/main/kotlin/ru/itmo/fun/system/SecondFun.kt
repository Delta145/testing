package ru.itmo.`fun`.system

import ru.itmo.`fun`.Fun
import ru.itmo.`fun`.log.Ln
import ru.itmo.`fun`.log.Log10
import ru.itmo.`fun`.log.Log2
import ru.itmo.`fun`.log.Log5
import kotlin.math.pow

open class SecondFun(
    accuracy: Double,
    private val ln: Ln = Ln(accuracy),
    private val log2: Log2 = Log2(accuracy),
    private val log5: Log5 = Log5(accuracy),
    private val log10: Log10 = Log10(accuracy),
) : Fun(accuracy) {
    override fun invoke(x: Double): Double {
        return (((log5(x) + log10(x)).pow(2) - log2(x)) - (log5(x) / ln(x))) + ln(x).pow(2)
    }
}