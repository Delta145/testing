package ru.itmo.domain

// interface marker
interface Earth : StressAffection

class CornFlakes(
    val weight: Double
) : Earth {
    override fun getStressAffection(): Double {
        return weight * 0.5
    }
}