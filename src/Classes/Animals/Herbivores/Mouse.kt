package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import IslandLogic.Configuration.AnimalsInfo.Mouse as MouseConfig
import Classes.Herbivore

class Mouse : Herbivore(
    weight = MouseConfig.weight,
    maxPopulationPerCell = MouseConfig.maximum_on_cell,
    speed = MouseConfig.speed,
    foodRequired = MouseConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Mouse()
    }
} 