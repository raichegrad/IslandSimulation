package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Rabbit as RabbitConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

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