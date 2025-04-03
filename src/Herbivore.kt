package org.example.Classes

import Animal
import com.javarush.island.entities.Cell
import com.javarush.island.entities.Logs
import kotlin.random.Random

abstract class Herbivore(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double
) : Animal(weight, maxPopulationPerCell, speed, foodRequired) {

    override fun eat(cell: Cell): Any? {
        if (!isAlive || currentFood >= foodRequired * 0.8) return null

        val plants = cell.getAllPlants()
        val plant = plants.values
            .flatten()
            .filter { it.alive }
            .shuffled()
            .firstOrNull() ?: return null

        plant.die()
        currentFood = minOf(foodRequired, currentFood + plant.weight)
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
        Logs.logBirth(this, offspring)
        return offspring
    }

    protected abstract fun createOffspring(): Animal
} 