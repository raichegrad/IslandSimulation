package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import IslandLogic.Configuration.AnimalsInfo.Buffalo as BuffaloConfig
import Classes.Herbivore

class Buffalo : Herbivore(
    weight = BuffaloConfig.weight,
    maxPopulationPerCell = BuffaloConfig.maximum_on_cell,
    speed = BuffaloConfig.speed,
    foodRequired = BuffaloConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Buffalo()
    }
} 