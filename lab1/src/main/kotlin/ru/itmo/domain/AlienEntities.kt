package ru.itmo.domain

import kotlin.math.abs

// interface marker
interface Alien : StressAffection

enum class FishSize {
    SMALL,
    MEDIUM,
    BIG,
    LARGE,
}

class BetelgeuseFish(
    private val color: String,
    private val size: FishSize,
) : Alien {
    override fun getStressAffection(): Double {
        return abs(color.hashCode() % 100.0) * (size.ordinal + 1)
    }
}

class BetelgeuseGlassBottle(
    private val fish: BetelgeuseFish,
) : Alien {
    override fun getStressAffection(): Double {
        return fish.getStressAffection() + 5.0
    }
}