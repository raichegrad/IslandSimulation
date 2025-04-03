package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import MainLogic.Configuration.AnimalsInfo.Duck as DuckConfig
import Classes.Herbivore

class Duck : Herbivore(
    weight = DuckConfig.weight,
    maxPopulationPerCell = DuckConfig.maximum_on_cell,
    speed = DuckConfig.speed,
    foodRequired = DuckConfig.required_food
) {
    override val preyTypes = listOf(
        Caterpillar::class.java.simpleName
    )

    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Caterpillar::class.java.simpleName -> 90
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Duck()
    }
} 