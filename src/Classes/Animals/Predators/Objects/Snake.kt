package com.javarush.island.Classes.Animals.Predators.Objects

import Classes.Animal
import com.javarush.island.Classes.Animals.Herbivores.Objects.Caterpillar
import com.javarush.island.Classes.Animals.Herbivores.Objects.Duck
import com.javarush.island.Classes.Animals.Herbivores.Objects.Mouse
import com.javarush.island.Classes.Animals.Herbivores.Objects.Rabbit
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Snake as SnakeConfig
import com.javarush.island.Classes.Animals.Predators.Predator

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

    override fun NewAnimal(): Animal {
        return Snake()
    }
} 