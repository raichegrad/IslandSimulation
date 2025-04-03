package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Mouse as MouseConfig
import com.javarush.island.entities.*
import org.example.Classes.Herbivore

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