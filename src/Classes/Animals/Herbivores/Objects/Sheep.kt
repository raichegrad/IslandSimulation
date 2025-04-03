package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Sheep as SheepConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

class Sheep : Herbivore(
    weight = SheepConfig.weight,
    maxPopulationPerCell = SheepConfig.maximum_on_cell,
    speed = SheepConfig.speed,
    foodRequired = SheepConfig.required_food
) {
    override fun NewAnimal(): Animal {
        return Sheep()
    }
} 