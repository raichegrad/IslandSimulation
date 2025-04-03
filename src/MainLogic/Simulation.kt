
package MainLogic

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
                    ::createEatingTasks,      // Сначала питание
                    ::createReproductionTasks, // Затем размножение
                    ::createMovementTasks,     // Потом перемещение
                    ::createPlantTasks,        // Рост растений
                    ::createHungryTasks        // Проверка голода
                ).forEach { taskCreator ->
                    val tasks = taskCreator()
                    animalProcessor.invokeAll(tasks)
                }

                // Вывод статистики
                println("=".repeat(18))
                printStatistics()
                println("=".repeat(18))
                println()
                println()
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
                                    is Animal -> cell.removeAnimal(eaten)
                                    is Plant -> cell.removePlant(eaten)
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

        val predators = setOf("Wolf", "Snake", "Fox", "Bear", "Eagle")
        val herbivores = setOf("Horse", "Deer", "Rabbit", "Mouse", "Goat", 
                              "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar")

        val groupedStats = stats.entries.groupBy { (type, _) -> 
            when {
                type == "Plant" -> "Растения"
                type in predators -> "Хищники"
                type in herbivores -> "Травоядные"
                else -> "Другие"
            }
        }

        val categories = listOf("Хищники", "Травоядные", "Растения")

        categories.forEach { category ->
            println("\n$category:")
            println("-".repeat(category.length + 1))
            
            groupedStats[category]?.let { entries ->
                entries.sortedBy { it.key }.forEach { (type, count) ->
                    val emoji = EmojiUtils.getEmoji(type)
                    println("$emoji $type: $count")
                }
            }
        }
    }
}