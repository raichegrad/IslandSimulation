package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Rabbit as RabbitConfig
import org.example.Classes.Herbivore

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