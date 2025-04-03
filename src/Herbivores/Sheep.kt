package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Sheep as SheepConfig
import com.javarush.island.entities.*
import org.example.Classes.Herbivore

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