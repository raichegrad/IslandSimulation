package IslandLogic

import Classes.Animal
import Classes.Plant
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Callable

class Simulation(
    private val island: Island,
    private val simulationPeriod: Long = 1000L
) {
    private val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private val animalProcessor: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private var isRunning = false

    fun start() {
        if (isRunning) return
        isRunning = true

        scheduler.scheduleAtFixedRate({
            if (!isRunning) {
                scheduler.shutdown()
                animalProcessor.shutdown()
                return@scheduleAtFixedRate
            }

            println("Статистика острова")

            synchronized(this) {
                listOf(
                    ::createEatingTasks,
                    ::createReproductionTasks,
                    ::createMovementTasks,
                    ::createPlantTasks,
                    ::createHungryTasks
                ).forEach { taskCreator ->
                    val tasks = taskCreator()
                    animalProcessor.invokeAll(tasks)
                }

                println("=".repeat(20))
                printStatistics()
                println("=".repeat(20))
                println()

                Thread.sleep(125)
            }
        }, 0, simulationPeriod, TimeUnit.MILLISECONDS)
    }

    private fun createEatingTasks(): List<Callable<Unit>> {
        return island.getAllCells().flatMap { cell ->
            cell.getAllAnimals().values.flatten()
                .filter { it.isAlive }
                .map { animal ->
                    Callable {
                        synchronized(cell) {
                            animal.eat(cell)?.let { eaten ->
                                when (eaten) {
                                    is Animal -> {
                                        Logs.logEating(animal, eaten)
                                        cell.removeAnimal(eaten)
                                    }
                                    is Plant -> {
                                        Logs.logEating(animal, eaten)
                                        cell.removePlant(eaten)
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun createReproductionTasks(): List<Callable<Unit>> {
        return island.getAllCells().flatMap { cell ->
            cell.getAllAnimals().values.flatten()
                .filter { it.isAlive }
                .map { animal ->
                    Callable {
                        synchronized(cell) {
                            animal.reproduce(cell)?.let { offspring ->
                                cell.addAnimal(offspring)
                                Logs.logBirth(animal, offspring)
                            }
                        }
                    }
                }
        }
    }

    private fun createMovementTasks(): List<Callable<Unit>> {
        return island.getAllCells().flatMap { cell ->
            cell.getAllAnimals().values.flatten()
                .filter { it.isAlive }
                .map { animal ->
                    Callable {
                        synchronized(cell) {
                            val newCell = animal.move(cell, island)
                            if (newCell != cell) {
                                cell.removeAnimal(animal)
                                newCell.addAnimal(animal)
                            }
                        }
                    }
                }
        }
    }

    private fun createHungryTasks(): List<Callable<Unit>> {
        return island.getAllCells().flatMap { cell ->
            cell.getAllAnimals().values.flatten()
                .filter { it.isAlive }
                .map { animal ->
                    Callable {
                        synchronized(cell) {
                            if (animal.isHungry()) {
                                animal.die()
                                cell.removeAnimal(animal)
                                Logs.logDeath(animal)
                            }
                        }
                    }
                }
        }
    }

    private fun createPlantTasks(): List<Callable<Unit>> {
        return island.getAllCells().flatMap { cell ->
            cell.getAllPlants().values.flatten()
                .filter { it.alive }
                .map { plant ->
                    Callable {
                        synchronized(cell) {
                            plant.reproduce(cell)?.let { newPlant ->
                                cell.addPlant(newPlant)
                            }
                        }
                    }
                }
        }
    }

    private fun printStatistics() {
        val stats = island.getStatistics()
        stats.entries
            .sortedBy { it.key }
            .forEach { (type, count) ->
                val emoji = when(type) {
                    "Wolf" -> "🐺"
                    "Snake" -> "🐍"
                    "Fox" -> "🦊"
                    "Bear" -> "🐻"
                    "Eagle" -> "🦅"
                    "Horse" -> "🐎"
                    "Deer" -> "🦌"
                    "Rabbit" -> "🐇"
                    "Mouse" -> "🐁"
                    "Goat" -> "🐐"
                    "Sheep" -> "🐑"
                    "Boar" -> "🐗"
                    "Buffalo" -> "🐃"
                    "Duck" -> "🦆"
                    "Caterpillar" -> "🐛"
                    "Plant" -> "🌿"
                    else -> ""
                }
                println("$emoji $type: $count")
            }
    }
}