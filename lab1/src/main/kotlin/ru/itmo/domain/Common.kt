package ru.itmo.domain

interface StressAffection {
    fun getStressAffection(): Double
}

// marker
interface Humanoid {
    fun updateStressState(level: Double = 0.0)
    fun lookAt(entity: StressAffection)
    val name: String
    var stressLevel: Double
}