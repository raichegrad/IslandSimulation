package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Horse as HorseConfig
import com.javarush.island.entities.*
import org.example.Classes.Herbivore

class Horse : Herbivore(
    weight = HorseConfig.weight,
    maxPopulationPerCell = HorseConfig.maximum_on_cell,
    speed = HorseConfig.speed,
    foodRequired = HorseConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Horse()
    }
} 