package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import MainLogic.Configuration.AnimalsInfo.Sheep as SheepConfig
import Classes.Herbivore

class Sheep : Herbivore(
    weight = SheepConfig.weight,
    maxPopulationPerCell = SheepConfig.maximum_on_cell,
    speed = SheepConfig.speed,
    foodRequired = SheepConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Sheep()
    }
} 