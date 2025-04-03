package com.javarush.island.entities.animals.predators

import Classes.Animal
import IslandLogic.Configuration.AnimalsInfo.Snake as SnakeConfig
import com.javarush.island.entities.animals.herbivores.*
import Classes.Predator

class Snake : Predator(
    weight = SnakeConfig.weight,
    maxPopulationPerCell = SnakeConfig.maximum_on_cell,
    speed = SnakeConfig.speed,
    foodRequired = SnakeConfig.required_food,
    preyTypes = listOf(
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Duck::class.java.simpleName,
        Caterpillar::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Rabbit::class.java.simpleName -> 20
        Mouse::class.java.simpleName -> 40
        Duck::class.java.simpleName -> 10
        Caterpillar::class.java.simpleName -> 80
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Snake()
    }
} 