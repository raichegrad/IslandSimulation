/**
 * Главный файл симуляции острова.
 * Отвечает за инициализацию и запуск симуляции.
 */
package com.javarush.island

import Classes.Animal
import Classes.Plant
import MainLogic.Island
import MainLogic.Configuration
import com.javarush.island.entities.animals.predators.*
import com.javarush.island.entities.animals.herbivores.*
import MainLogic.Simulation
import kotlin.random.Random

/**
 * Точка входа в программу.
 * Инициализирует остров, населяет его животными и растениями, запускает симуляцию.
 */
fun main() {
    val island = createIsland()
    populateIsland(island)
    startSimulation(island)
}

/**
 * Создает новый остров с заданными размерами
 */
private fun createIsland(): Island {
    return Island(Configuration.width, Configuration.height)
}

/**
 * Заполняет остров животными и растениями
 */
private fun populateIsland(island: Island) {
    // Определяем фабрики для создания животных
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

    // Заселяем остров животными
    populateWithAnimals(island, animalFactories)
    
    // Добавляем растения
    populateWithPlants(island)
}

/**
 * Заселяет остров животными
 */
private fun populateWithAnimals(island: Island, factories: AnimalFactories) {
    // Заселяем травоядных
    factories.herbivores.forEach { creator ->
        repeat(40) {
            addRandomly(island, creator())
        }
    }

    // Заселяем хищников
    factories.predators.forEach { creator ->
        repeat(25) {
            addRandomly(island, creator())
        }
    }
}

/**
 * Добавляет растения на остров
 */
private fun populateWithPlants(island: Island) {
    repeat(100) {
        addRandomly(island, Plant())
    }
}

/**
 * Запускает симуляцию
 */
private fun startSimulation(island: Island) {
    val simulation = Simulation(island, Configuration.period)
    simulation.start()
}

/**
 * Добавляет объект в случайную клетку острова
 */
private fun addRandomly(island: Island, entity: Any) {
    val x = Random.nextInt(Configuration.width)
    val y = Random.nextInt(Configuration.height)
    when (entity) {
        is Animal -> island.addAnimal(entity, x, y)
        is Plant -> island.addPlant(entity, x, y)
    }
}

/**
 * Класс для хранения фабрик создания животных
 */
private data class AnimalFactories(
    val herbivores: List<() -> Animal>,
    val predators: List<() -> Animal>
)