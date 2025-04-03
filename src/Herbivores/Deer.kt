package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import IslandLogic.Configuration.AnimalCharacteristics.Deer as DeerConfig
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