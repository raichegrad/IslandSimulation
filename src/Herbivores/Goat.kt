package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Goat as GoatConfig
import org.example.Classes.Herbivore

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