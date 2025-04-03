package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Duck as DuckConfig
import org.example.Classes.Herbivore

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