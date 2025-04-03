package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Boar as BoarConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

class Boar : Herbivore(
    weight = BoarConfig.weight,
    maxPopulationPerCell = BoarConfig.maximum_on_cell,
    speed = BoarConfig.speed,
    foodRequired = BoarConfig.required_food
) {
    override fun NewAnimal(): Animal {
        return Boar()
    }
} 