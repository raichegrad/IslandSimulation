package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Deer as DeerConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

class Deer : Herbivore(
    weight = DeerConfig.weight,
    maxPopulationPerCell = DeerConfig.maximum_on_cell,
    speed = DeerConfig.speed,
    foodRequired = DeerConfig.required_food
) {
    override fun NewAnimal(): Animal {
        return Deer()
    }
} 