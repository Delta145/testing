package ru.itmo.domain

enum class HumanState(
    val stressLevel: Double
) {
    CONFIDENT(0.0),
    CALM(10.0),
    NERVOUS(25.0),
    ANXIOUS(50.0),
    MADNESS(150.0),
}

class Human(
    override val name: String,
    override var stressLevel: Double,
) : Earth, Humanoid {
    var state: HumanState = HumanState.CONFIDENT
        private set

    init {
        updateStressState()
    }

    override fun getStressAffection(): Double {
        return (10.0 - minOf(stressLevel, 10.0))
    }

    override fun updateStressState(level: Double) {
        stressLevel += level
        if (stressLevel < 0.0)
            stressLevel = 0.0

        HumanState.values().forEach { s ->
            if (stressLevel >= s.stressLevel) state = s
        }
    }

    override fun lookAt(entity: StressAffection) {
        if (entity is Earth) {
            updateStressState(-entity.getStressAffection())
        } else {
            updateStressState(entity.getStressAffection())
        }
    }
}