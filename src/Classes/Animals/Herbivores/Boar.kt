package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import IslandLogic.Configuration.AnimalCharacteristics.Boar as BoarConfig
import Classes.Herbivore

class Boar : Herbivore(
    weight = BoarConfig.weight,
    maxPopulationPerCell = BoarConfig.maximum_on_cell,
    speed = BoarConfig.speed,
    foodRequired = BoarConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Boar()
    }
} 