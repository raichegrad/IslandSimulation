package Classes

import kotlin.random.Random
import com.javarush.island.MainLogic.Island.Cell
import com.javarush.island.MainLogic.Config.Configuration.PlantsInfo.Plant as PlantConfig

class Plant(
    val weight: Double = PlantConfig.weight.toDouble(),
    private val maxInCell: Int = PlantConfig.maximum_on_cell
) {
    var alive = true

    fun reproduce(cell: Cell): Plant? {
        if (!alive) return null

        val currentCount = cell.getAllPlants().values.flatten().size
        if (currentCount >= maxInCell || Random.nextDouble() > 0.2) return null

        return Plant(weight, maxInCell)
    }

    fun die() {
        alive = false
    }
}