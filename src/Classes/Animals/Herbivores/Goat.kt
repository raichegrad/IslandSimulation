package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import IslandLogic.Configuration.AnimalsInfo.Goat as GoatConfig
import Classes.Herbivore

class Goat : Herbivore(
    weight = GoatConfig.weight,
    maxPopulationPerCell = GoatConfig.maximum_on_cell,
    speed = GoatConfig.speed,
    foodRequired = GoatConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Goat()
    }
} 