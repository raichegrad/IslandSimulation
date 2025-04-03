package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Deer as DeerConfig
import org.example.Classes.Herbivore

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