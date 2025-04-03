package com.javarush.island.entities.animals.herbivores

import Animal
import com.javarush.island.config.Configuration.AnimalCharacteristics.Boar as BoarConfig
import com.javarush.island.entities.*
import org.example.Classes.Herbivore

class Boar : Herbivore(
    weight = BoarConfig.weight,
    maxPopulationPerCell = BoarConfig.maximum_on_cell,
    speed = BoarConfig.speed,
    foodRequired = BoarConfig.required_food
) {
    override fun createOffspring(): Animal {
        return Boar()
    }
} 