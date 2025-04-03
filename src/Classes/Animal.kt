package Classes

import com.javarush.island.MainLogic.Island.Cell
import com.javarush.island.MainLogic.Island.Island
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
        return newLocation
    }

    open fun reproduce(cell: Cell): Animal? {
        if (!isAlive || currentFood < foodRequired * 0.4) return null

        val animals = cell.getAllAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        if (Random.nextDouble() > 0.25) return null

        return null
    }

    open fun isHungry(): Boolean {
        if (!isAlive) return false
        currentFood -= foodRequired * 0.1
        return currentFood <= 0
    }

    fun die() {
        if (isAlive) {
            isAlive = false
        }
    }
}