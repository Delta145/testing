package ru.itmo.domain

enum class BetelgeuseManState(
    val stressLevel: Double
) {
    CONFIDENT(0.0),
    A_BIT_NERVOUS(500.0),
}

class BetelgeuseMan(
    override val name: String,
    private val bottle: BetelgeuseGlassBottle,
    override var stressLevel: Double,
) : Alien, Humanoid {
    var state: BetelgeuseManState = BetelgeuseManState.CONFIDENT
        private set
    init {
        updateStressState()
    }

    override fun getStressAffection(): Double {
        return bottle.getStressAffection() + 10.0
    }

    fun suggestPutInEar(humanoid: Humanoid) {
        if (humanoid is Earth) {
            humanoid.updateStressState(getStressAffection() * 3)
        } else {
            humanoid.updateStressState(-0.5 * getStressAffection())
        }
    }

    override fun updateStressState(level: Double) {
        stressLevel += if (level > 0) level / 10 else level
        if (stressLevel < 0)
            stressLevel = 0.0

        BetelgeuseManState.values().forEach { s ->
            if (stressLevel > s.stressLevel) state = s
        }
    }

    override fun lookAt(entity: StressAffection) {
        if (entity is Alien) {
            updateStressState(-entity.getStressAffection())
        } else {
            updateStressState(entity.getStressAffection())
        }
    }

}