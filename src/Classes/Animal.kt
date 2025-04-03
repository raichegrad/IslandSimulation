package Classes

import IslandLogic.Cell
import IslandLogic.Island
import IslandLogic.Logs
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

    fun move(currentCell: Cell, island: Island): Cell {
        if (!isAlive) return currentCell

        val possibleMoves = island.getPossibleMoves(currentCell, speed)
        if (possibleMoves.isEmpty()) return currentCell

        val newLocation = possibleMoves.random()
        if (newLocation != currentCell) {
            Logs.logMovement(
                this,
                currentCell.x, currentCell.y,
                newLocation.x, newLocation.y
            )
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

    fun die(cause: String = "голод") {
        if (isAlive) {
            isAlive = false
            Logs.logDeath(this, cause)
        }
    }
}