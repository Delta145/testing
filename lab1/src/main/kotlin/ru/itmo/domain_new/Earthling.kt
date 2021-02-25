package ru.itmo.domain_new

data class EarthThing(
    val thingName: String,
)

class Human(
    private val name: String,
    var isConfident: Boolean = false,
) {
    fun lookAt(obj: Any): ActionResult {
        if (obj is AlienThing || obj is BetelgeuseMan) {
            isConfident = false
        } else if (obj is Human || obj is EarthThing) {
            isConfident = true
        }
        return ActionResult("$name смотрел на него, моргая глазами.", true)
    }

    fun getStateDescription(): String {
        return if (isConfident)
            "$name счастлив!"
        else
            "Ему хотелось, чтобы здесь было что-нибудь простое и знакомое, за что можно было бы мысленно зацепиться."
    }

    fun analyzeEnv(env: Environment): String {
        return if (env.cornFlakes != null) {
            isConfident = true
            "Не смотря на всю враждебность обстановки Артур спокоен -- рядом есть ${env.cornFlakes}"
        } else {
            isConfident = false
            "Он чувствовал бы себя намного увереннее, если бы рядом с ${env.dentrassi}, ${env.matrix} и человеком с Бетельгейзе, держащим маленькую" +
                    " рыбку и предлагающим засунуть её в ухо, он увидел, к примеру, пакет кукурузных хлопьев."
        }
    }
}