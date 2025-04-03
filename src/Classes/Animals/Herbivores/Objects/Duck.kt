package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Duck as DuckConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

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