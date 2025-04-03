package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import MainLogic.Configuration.AnimalsInfo.Rabbit as RabbitConfig
import Classes.Herbivore

class Rabbit : Herbivore(
    weight = RabbitConfig.weight,
    maxPopulationPerCell = RabbitConfig.maximum_on_cell,
    speed = RabbitConfig.speed,
    foodRequired = RabbitConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Rabbit()
    }
} 