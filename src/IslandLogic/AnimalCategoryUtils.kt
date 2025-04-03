package IslandLogic

object AnimalCategoryUtils {
    private val predators = setOf(
        "Wolf", "Snake", "Fox", "Bear", "Eagle"
    )
    
    private val herbivores = setOf(
        "Horse", "Deer", "Rabbit", "Mouse", "Goat", 
        "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar"
    )
    
    fun getCategory(type: String): String {
        return when {
            type == "Plant" -> "Растения"
            type in predators -> "Хищники"
            type in herbivores -> "Травоядные"
            else -> "Другие"
        }
    }
} 