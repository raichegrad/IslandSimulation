package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import IslandLogic.Configuration.AnimalCharacteristics.Horse as HorseConfig
import Classes.Herbivore

class Horse : Herbivore(
    weight = HorseConfig.weight,
    maxPopulationPerCell = HorseConfig.maximum_on_cell,
    speed = HorseConfig.speed,
    foodRequired = HorseConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Horse()
    }
} 