package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Goat as GoatConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

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