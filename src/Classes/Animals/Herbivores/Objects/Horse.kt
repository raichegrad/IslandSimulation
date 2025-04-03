package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Horse as HorseConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

class Horse : Herbivore(
    weight = HorseConfig.weight,
    maxPopulationPerCell = HorseConfig.maximum_on_cell,
    speed = HorseConfig.speed,
    foodRequired = HorseConfig.required_food
) {
    override fun NewAnimal(): Animal {
        return Horse()
    }
} 