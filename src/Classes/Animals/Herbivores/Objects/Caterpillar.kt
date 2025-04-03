package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Island.Cell
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Caterpillar as CaterpillarConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore
import kotlin.random.Random

class Caterpillar : Herbivore(
    weight = CaterpillarConfig.weight,
    maxPopulationPerCell = CaterpillarConfig.maximum_on_cell,
    speed = CaterpillarConfig.speed,
    foodRequired = CaterpillarConfig.required_food
) {
    private var lifespan = Random.nextInt(5, 10)
    private var currentAge = 0

    init {
        currentFood = 1.0
    }

    override fun isHungry(): Boolean {
        if (!isAlive) return false

        currentAge++
        if (currentAge >= lifespan) {
            die()
            return true
        }

        return false
    }

    override fun eat(cell: Cell): Any? {
        return null
    }

    override fun reproduce(cell: Cell): Animal? {
        if (!isAlive) return null

        val animals = cell.getAllAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        if (Random.nextDouble() > 0.25) return null

        return NewAnimal()
    }

    override fun NewAnimal(): Animal {
        return Caterpillar()
    }
} 