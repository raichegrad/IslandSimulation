package com.javarush.island

import Classes.Animal
import Classes.Plant
import IslandLogic.Island
import IslandLogic.Configuration
import com.javarush.island.entities.animals.predators.*
import com.javarush.island.entities.animals.herbivores.*
import IslandLogic.Simulation
import kotlin.random.Random

fun main() {
    val island = Island(Configuration.width, Configuration.height)

    repeat(40) {
        addRandomly(island, Caterpillar())
        addRandomly(island, Horse())
        addRandomly(island, Deer())
        addRandomly(island, Rabbit())
        addRandomly(island, Mouse())
        addRandomly(island, Goat())
        addRandomly(island, Sheep())
        addRandomly(island, Boar())
        addRandomly(island, Buffalo())
        addRandomly(island, Duck())
    }

    repeat(25) {
        addRandomly(island, Wolf())
        addRandomly(island, Bear())
        addRandomly(island, Fox())
        addRandomly(island, Eagle())
        addRandomly(island, Snake())
    }

    repeat(100) {
        val x = Random.nextInt(Configuration.width)
        val y = Random.nextInt(Configuration.height)
        island.addPlant(Plant(), x, y)
    }

    val simulation = Simulation(island, Configuration.period)
    simulation.start()
}

private fun addRandomly(island: Island, animal: Animal) {
    val x = Random.nextInt(Configuration.width)
    val y = Random.nextInt(Configuration.height)
    island.addAnimal(animal, x, y)
}