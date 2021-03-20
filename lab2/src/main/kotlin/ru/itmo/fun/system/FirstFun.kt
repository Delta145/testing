package ru.itmo.`fun`.system

import ru.itmo.`fun`.Fun
import ru.itmo.`fun`.trig.Csc
import kotlin.math.pow

open class FirstFun(
    accuracy: Double,
    private val csc: Csc = Csc(accuracy)
) : Fun(accuracy) {

    override fun invoke(x: Double): Double {
        return csc(x).pow(3)
    }
}