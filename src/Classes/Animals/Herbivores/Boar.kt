package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import MainLogic.Configuration.AnimalsInfo.Boar as BoarConfig
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