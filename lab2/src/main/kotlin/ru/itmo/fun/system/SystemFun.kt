package ru.itmo.`fun`.system

import ru.itmo.`fun`.Fun

open class SystemFun(
    accuracy: Double,
    private val firstFun: FirstFun = FirstFun(accuracy),
    private val secondFun: SecondFun = SecondFun(accuracy),
) : Fun(accuracy) {
    override fun invoke(x: Double): Double {
        return if (x <= 0.0) firstFun(x) else secondFun(x)
    }
}