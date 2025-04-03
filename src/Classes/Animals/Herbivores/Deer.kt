package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import MainLogic.Configuration.AnimalsInfo.Deer as DeerConfig
import Classes.Herbivore

class Deer : Herbivore(
    weight = DeerConfig.weight,
    maxPopulationPerCell = DeerConfig.maximum_on_cell,
    speed = DeerConfig.speed,
    foodRequired = DeerConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Deer()
    }
} 