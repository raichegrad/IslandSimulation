package MainLogic

object EmojiUtils {
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

    fun getEmoji(name: String?): String {
        return animalEmojis[name] ?: ""
    }
} 