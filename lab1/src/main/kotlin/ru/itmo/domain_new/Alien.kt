package ru.itmo.domain_new

class AlienThing(
    val thingName: String,
    val thingDescription: String,
) {
    override fun toString(): String {
        return "$thingName $thingDescription"
    }
}

data class ActionResult(
    val resultDescription: String,
    val isSuccess: Boolean,
)

class BetelgeuseMan(
    private val name: String,
) {
    var holdingThing: AlienThing? = null

    fun takeThing(thing: AlienThing): ActionResult {
        return if (holdingThing == null) {
            this.holdingThing = thing
            ActionResult("У ${name}а в руке был $thing", true)
        } else {
            ActionResult("", false)
        }
    }
}
