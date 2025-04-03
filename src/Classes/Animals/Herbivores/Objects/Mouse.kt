package com.javarush.island.Classes.Animals.Herbivores.Objects

import Classes.Animal
import com.javarush.island.MainLogic.Config.Configuration.AnimalsInfo.Mouse as MouseConfig
import com.javarush.island.Classes.Animals.Herbivores.Herbivore

class Mouse : Herbivore(
    weight = MouseConfig.weight,
    maxPopulationPerCell = MouseConfig.maximum_on_cell,
    speed = MouseConfig.speed,
    foodRequired = MouseConfig.required_food
) {
    override fun NewAnimal(): Animal {
        return Mouse()
    }
} 