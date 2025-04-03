package com.javarush.island.Classes.Animals.Predators.Objects

import Classes.Animal
import com.javarush.island.Classes.Animals.Herbivores.Objects.*
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Bear as BearConfig
import com.javarush.island.Classes.Animals.Predators.Predator

class Bear : Predator(
    weight = BearConfig.weight,
    maxPopulationPerCell = BearConfig.maximum_on_cell,
    speed = BearConfig.speed,
    foodRequired = BearConfig.required_food,
    preyTypes = listOf(
        Horse::class.java.simpleName,
        Deer::class.java.simpleName,
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Goat::class.java.simpleName,
        Sheep::class.java.simpleName,
        Boar::class.java.simpleName,
        Buffalo::class.java.simpleName,
        Duck::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Horse::class.java.simpleName -> 40
        Deer::class.java.simpleName -> 80
        Rabbit::class.java.simpleName -> 80
        Mouse::class.java.simpleName -> 90
        Goat::class.java.simpleName -> 70
        Sheep::class.java.simpleName -> 70
        Boar::class.java.simpleName -> 50
        Buffalo::class.java.simpleName -> 20
        Duck::class.java.simpleName -> 10
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Bear()
    }
} 