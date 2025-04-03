package IslandLogic

import Classes.Animal
import Classes.Plant

class Island(val width: Int, val height: Int) {
    private val cells: Array<Array<Cell>> = Array(width) { x -> 
        Array(height) { y -> Cell(x, y) }
    }

    fun getCells(x: Int, y: Int): Cell {
        return cells[x][y]
    }

    fun getAllCells(): List<Cell> {
        return cells.flatten()
    }

    fun addAnimal(animal: Animal, x: Int, y: Int) {
        cells[x][y].addAnimal(animal)
    }

    fun addPlant(plant: Plant, x: Int, y: Int) {
        cells[x][y].addPlant(plant)
    }

    fun getStatistics(): Map<String, Int> {
        val stats = mutableMapOf<String, Int>()

        getAllCells().forEach { cell ->
            cell.getAllAnimals().forEach { (type, animals) ->
                val key = type.simpleName ?: "Не определено"
                stats[key] = (stats[key] ?: 0) + animals.size
            }

            cell.getAllPlants().forEach { (type, plants) ->
                val key = type.simpleName ?: "Не определено"
                stats[key] = (stats[key] ?: 0) + plants.size
            }
        }

        return stats
    }

    fun getPossibleMoves(currentCell: Cell, speed: Int): List<Cell> {
        val possibleMoves = mutableListOf<Cell>()
        val currentX = currentCell.x
        val currentY = currentCell.y

        for (x in maxOf(0, currentX - speed)..minOf(width - 1, currentX + speed)) {
            for (y in maxOf(0, currentY - speed)..minOf(height - 1, currentY + speed)) {
                if (x != currentX || y != currentY) {
                    possibleMoves.add(getCells(x, y))
                }
            }
        }

        return possibleMoves
    }
} 