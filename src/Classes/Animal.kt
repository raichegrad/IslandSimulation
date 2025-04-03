package Classes

import MainLogic.Cell
import MainLogic.Island
import MainLogic.EmojiUtils
import kotlin.random.Random

abstract class Animal(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double
) {
    protected var currentFood: Double = foodRequired
    var weight: Double = weight
    protected val maxPopulationPerCell: Int = maxPopulationPerCell
    protected val speed: Int = speed
    protected val foodRequired: Double = foodRequired

    var isAlive = true

    abstract fun eat(cell: Cell): Any?

    protected fun logMovement(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        val animalType = this::class.simpleName
        val emoji = EmojiUtils.getEmoji(animalType)
    }

    protected fun logDeath() {
        val animalType = this::class.simpleName
        val emoji = EmojiUtils.getEmoji(animalType)
    }

    protected fun logEating(prey: Any) {
        val predatorType = this::class.simpleName
        val predatorEmoji = EmojiUtils.getEmoji(predatorType)
        
        val (preyName, preyEmoji) = when(prey) {
            is Animal -> prey::class.simpleName to EmojiUtils.getEmoji(prey::class.simpleName)
            is Plant -> "Растение" to "🌿"
            else -> return
        }
    }

    protected fun logBirth(offspring: Animal) {
        val animalType = this::class.simpleName
        val emoji = EmojiUtils.getEmoji(animalType)
    }

    fun move(currentCell: Cell, island: Island): Cell {
        if (!isAlive) return currentCell

        val possibleMoves = island.getPossibleMoves(currentCell, speed)
        if (possibleMoves.isEmpty()) return currentCell

        val newLocation = possibleMoves.random()
        if (newLocation != currentCell) {
            logMovement(currentCell.x, currentCell.y, newLocation.x, newLocation.y)
        }
        return newLocation
    }

    open fun reproduce(cell: Cell): Animal? {
        if (!isAlive || currentFood < foodRequired * 0.4) return null

        val animals = cell.getAllAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        if (Random.nextDouble() > 0.25) return null

        return try {
            val constructor = this::class.constructors.first()
            constructor.call()
        } catch (e: Exception) {
            null
        }
    }

    open fun isHungry(): Boolean {
        if (!isAlive) return false
        currentFood -= foodRequired * 0.1
        return currentFood <= 0
    }

    fun die() {
        if (isAlive) {
            isAlive = false
            logDeath()
        }
    }
}