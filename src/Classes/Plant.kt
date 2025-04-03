package Classes

import IslandLogic.Cell
import IslandLogic.Configuration.PlantCharacteristics.Plant as PlantConfig

class Plant(
    val weight: Double = PlantConfig.weight.toDouble(),
    val maxInCell: Int = PlantConfig.maximum_on_cell
) {
    var alive: Boolean = true

    fun reproduce(cell: Cell): Plant? {
        val currentPlants = cell.getAllPlants().values.flatten()

        val densityFactor = 1 - (currentPlants.size.toDouble() / maxInCell)
        val baseChance = 0.2 * densityFactor

        val deathChance = 0.15 * (currentPlants.size.toDouble() / maxInCell)

        return when {
            Math.random() < deathChance -> {
                cell.removePlant(this)
                null
            }
            Math.random() < baseChance -> Plant(weight, maxInCell)
            else -> null
        }
    }

    fun die() {
        alive = false
    }
}