package IslandLogic

import Classes.Animal
import Classes.Plant

object Logs {
    private val animalEmojis = mapOf(
        "Wolf" to "🐺",
        "Snake" to "🐍",
        "Fox" to "🦊",
        "Bear" to "🐻",
        "Eagle" to "🦅",
        "Horse" to "🐎",
        "Deer" to "🦌",
        "Rabbit" to "🐇",
        "Mouse" to "🐁",
        "Goat" to "🐐",
        "Sheep" to "🐑",
        "Boar" to "🐗",
        "Buffalo" to "🐃",
        "Duck" to "🦆",
        "Caterpillar" to "🐛",
        "Plant" to "🌿"
    )

    private fun getEmoji(name: String?): String {
        return animalEmojis[name] ?: ""
    }

    @Synchronized
    fun logEating(predator: Animal, prey: Any) {
        val predatorName = predator::class.simpleName
        val predatorEmoji = getEmoji(predatorName)
        
        val (preyName, preyEmoji) = when(prey) {
            is Animal -> prey::class.simpleName to getEmoji(prey::class.simpleName)
            is Plant -> "Растение" to "🌿"
            else -> return
        }
    }

    @Synchronized
    fun logBirth(parent: Animal, child: Animal) {
        val animalType = parent::class.simpleName
        val emoji = getEmoji(animalType)
    }

    @Synchronized
    fun logDeath(animal: Animal, cause: String = "голод") {
        val animalType = animal::class.simpleName
        val emoji = getEmoji(animalType)
    }

    @Synchronized
    fun logMovement(animal: Animal, fromX: Int, fromY: Int, toX: Int, toY: Int) {
        val animalType = animal::class.simpleName
        val emoji = getEmoji(animalType)
    }
} 