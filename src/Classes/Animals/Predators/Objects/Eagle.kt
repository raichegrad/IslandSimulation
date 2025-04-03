package com.javarush.island.Classes.Animals.Predators.Objects

import Classes.Animal
import com.javarush.island.Classes.Animals.Herbivores.Objects.Duck
import com.javarush.island.Classes.Animals.Herbivores.Objects.Mouse
import com.javarush.island.Classes.Animals.Herbivores.Objects.Rabbit
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Eagle as EagleConfig
import com.javarush.island.Classes.Animals.Predators.Predator

class Eagle : Predator(
    weight = EagleConfig.weight,
    maxPopulationPerCell = EagleConfig.maximum_on_cell,
    speed = EagleConfig.speed,
    foodRequired = EagleConfig.required_food,
    preyTypes = listOf(
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Duck::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Rabbit::class.java.simpleName -> 90
        Mouse::class.java.simpleName -> 90
        Duck::class.java.simpleName -> 80
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Eagle()
    }
} 