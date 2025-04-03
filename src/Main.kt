package com.javarush.island

import Classes.Animal
import Classes.Plant
import MainLogic.Island
import MainLogic.Configuration
import com.javarush.island.entities.animals.predators.*
import com.javarush.island.entities.animals.herbivores.*
import MainLogic.Simulation
import kotlin.random.Random

fun main() {
    val island = createIsland()
    populateIsland(island)
    startSimulation(island)
}

private fun createIsland(): Island {
    return Island(Configuration.width, Configuration.height)
}

private fun populateIsland(island: Island) {
    val animalFactories = AnimalFactories(
        herbivores = listOf(
            { Caterpillar() },
            { Horse() },
            { Deer() },
            { Rabbit() },
            { Mouse() },
            { Goat() },
            { Sheep() },
            { Boar() },
            { Buffalo() },
            { Duck() }
        ),
        predators = listOf(
            { Wolf() },
            { Bear() },
            { Fox() },
            { Eagle() },
            { Snake() }
        )
    )

    populateWithAnimals(island, animalFactories)

    populateWithPlants(island)
}

private fun populateWithAnimals(island: Island, factories: AnimalFactories) {
    factories.herbivores.forEach { creator ->
        repeat(40) {
            addRandomly(island, creator())
        }
    }

    factories.predators.forEach { creator ->
        repeat(25) {
            addRandomly(island, creator())
        }
    }
}

private fun populateWithPlants(island: Island) {
    repeat(100) {
        addRandomly(island, Plant())
    }
}

private fun startSimulation(island: Island) {
    val simulation = Simulation(island, Configuration.period)
    simulation.start()
}

private fun addRandomly(island: Island, entity: Any) {
    val x = Random.nextInt(Configuration.width)
    val y = Random.nextInt(Configuration.height)
    when (entity) {
        is Animal -> island.addAnimal(entity, x, y)
        is Plant -> island.addPlant(entity, x, y)
    }
}

private data class AnimalFactories(
    val herbivores: List<() -> Animal>,
    val predators: List<() -> Animal>
)