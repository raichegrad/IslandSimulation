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

    val herbivores = listOf(
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
    )
    
    val predators = listOf(
        { Wolf() },
        { Bear() },
        { Fox() },
        { Eagle() },
        { Snake() }
    )

    herbivores.forEach { creator ->
        (1..40).forEach { _ ->
            addRandomly(island, creator())
        }
    }

    predators.forEach { creator ->
        (1..25).forEach { _ ->
            addRandomly(island, creator())
        }
    }

    (1..100).forEach { _ ->
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