package Classes

import IslandLogic.Cell
import IslandLogic.Logs
import kotlin.random.Random
import java.util.concurrent.ThreadLocalRandom

abstract class Predator(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double,
    private val preyTypes: List<String>
) : Animal(weight, maxPopulationPerCell, speed, foodRequired) {

    protected abstract fun getEatingProbability(preyType: String): Int

    override fun eat(cell: Cell): Any? {
        if (!isAlive || currentFood >= foodRequired * 0.8) return null

        val animals = cell.getAllAnimals()
            .mapKeys { it.key.simpleName }
        val possiblePrey = animals.entries
            .filter { (type, _) -> type in preyTypes }
            .flatMap { (type, preyList) ->
                preyList.filter { prey ->
                    prey.isAlive && 
                    ThreadLocalRandom.current().nextInt(100) < getEatingProbability(type)
                }
            }
            .shuffled()
            .firstOrNull() ?: return null

        possiblePrey.die("охота")
        currentFood = minOf(foodRequired, currentFood + possiblePrey.weight)
        return possiblePrey
    }

    override fun reproduce(cell: Cell): Animal? {
        if (currentFood < foodRequired * 0.4) return null

        val animals = cell.getAllAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        if (Random.nextDouble() > 0.15) return null

        val offspring = createOffspring()
        Logs.logBirth(this, offspring)
        return offspring
    }

    protected abstract fun createOffspring(): Animal
}