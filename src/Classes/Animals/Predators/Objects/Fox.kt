package com.javarush.island.Classes.Animals.Predators.Objects

import Classes.Animal
import com.javarush.island.Classes.Animals.Herbivores.Objects.Caterpillar
import com.javarush.island.Classes.Animals.Herbivores.Objects.Duck
import com.javarush.island.Classes.Animals.Herbivores.Objects.Mouse
import com.javarush.island.Classes.Animals.Herbivores.Objects.Rabbit
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Fox as FoxConfig
import com.javarush.island.Classes.Animals.Predators.Predator

class Fox : Predator(
    weight = FoxConfig.weight,
    maxPopulationPerCell = FoxConfig.maximum_on_cell,
    speed = FoxConfig.speed,
    foodRequired = FoxConfig.required_food,
    preyTypes = listOf(
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Duck::class.java.simpleName,
        Caterpillar::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Rabbit::class.java.simpleName -> 70
        Mouse::class.java.simpleName -> 90
        Duck::class.java.simpleName -> 60
        Caterpillar::class.java.simpleName -> 40
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Fox()
    }
} 