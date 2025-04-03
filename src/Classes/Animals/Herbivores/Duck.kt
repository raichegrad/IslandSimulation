package com.javarush.island.entities.animals.herbivores

import Classes.Animal
import MainLogic.Configuration.AnimalsInfo.Duck as DuckConfig
import Classes.Herbivore

class Duck : Herbivore(
    weight = DuckConfig.weight,
    maxPopulationPerCell = DuckConfig.maximum_on_cell,
    speed = DuckConfig.speed,
    foodRequired = DuckConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Duck()
    }
} 