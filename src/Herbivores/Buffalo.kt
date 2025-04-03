package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Buffalo as BuffaloConfig
import org.example.Classes.Herbivore

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