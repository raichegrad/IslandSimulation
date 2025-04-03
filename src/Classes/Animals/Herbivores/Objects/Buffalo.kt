package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Buffalo as BuffaloConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

class Buffalo : Herbivore(
    weight = BuffaloConfig.weight,
    maxPopulationPerCell = BuffaloConfig.maximum_on_cell,
    speed = BuffaloConfig.speed,
    foodRequired = BuffaloConfig.required_food
) {
    override fun NewAnimal(): Animal {
        return Buffalo()
    }
} 