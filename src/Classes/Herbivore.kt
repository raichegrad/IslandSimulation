package Classes

import MainLogic.Cell
import kotlin.random.Random

abstract class Herbivore(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double
) : Animal(weight, maxPopulationPerCell, speed, foodRequired) {

    protected open val preyTypes: List<String> = emptyList()

    protected open fun getEatingProbability(preyType: String): Int = 0

    override fun eat(cell: Cell): Any? {
        if (!isAlive || currentFood >= foodRequired * 0.8) return null

        if (preyTypes.isNotEmpty()) {
            val animals = cell.getAllAnimals().mapKeys { it.key.simpleName }
            val possiblePrey = animals.entries
                .filter { (type, _) -> type in preyTypes }
                .flatMap { (type, preyList) ->
                    preyList.filter { prey ->
                        prey.isAlive && 
                        Random.nextInt(100) < getEatingProbability(type)
                    }
                }
                .shuffled()
                .firstOrNull()

            if (possiblePrey != null) {
                possiblePrey.die()
                currentFood = minOf(foodRequired, currentFood + possiblePrey.weight)
                logEating(possiblePrey)
                return possiblePrey
            }
        }

        val plants = cell.getAllPlants()
        val plant = plants.values.flatten().filter { it.alive }.shuffled().firstOrNull() ?: return null

        plant.die()
        currentFood = minOf(foodRequired, currentFood + plant.weight)
        logEating(plant)
        return plant
    }

    override fun reproduce(cell: Cell): Animal? {
        if (currentFood < foodRequired * 0.4) return null

        val animals = cell.getAllAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        if (Random.nextDouble() > 0.15) return null

        val offspring = createOffspring()
        logBirth(offspring)
        return offspring
    }

    protected abstract fun createOffspring(): Animal
} 