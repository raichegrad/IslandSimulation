package IslandLogic

object Configuration {
    const val width = 100
    const val height = 20
    const val period = 1000L

    object AnimalCharacteristics {
        object Wolf {
            const val weight = 50.0
            const val maximum_on_cell = 30
            const val speed = 3
            const val required_food = 8.0
        }

        object Snake {
            const val weight = 15.0
            const val maximum_on_cell = 30
            const val speed = 1
            const val required_food = 3.0
        }

        object Fox {
            const val weight = 8.0
            const val maximum_on_cell = 30
            const val speed = 2
            const val required_food = 2.0
        }

        object Bear {
            const val weight = 500.0
            const val maximum_on_cell = 5
            const val speed = 2
            const val required_food = 80.0
        }

        object Eagle {
            const val weight = 6.0
            const val maximum_on_cell = 20
            const val speed = 3
            const val required_food = 1.0
        }




        object Horse {
            const val weight = 400.0
            const val maximum_on_cell = 20
            const val speed = 4
            const val required_food = 60.0
        }

        object Deer {
            const val weight = 300.0
            const val maximum_on_cell = 20
            const val speed = 4
            const val required_food = 50.0
        }

        object Rabbit {
            const val weight = 2.0
            const val maximum_on_cell = 150
            const val speed = 2
            const val required_food = 0.45
        }

        object Mouse {
            const val weight = 0.05
            const val maximum_on_cell = 500
            const val speed = 1
            const val required_food = 0.01
        }

        object Goat {
            const val weight = 60.0
            const val maximum_on_cell = 140
            const val speed = 3
            const val required_food = 10.0
        }

        object Sheep {
            const val weight = 70.0
            const val maximum_on_cell = 140
            const val speed = 3
            const val required_food = 15.0
        }

        object Boar {
            const val weight = 400.0
            const val maximum_on_cell = 50
            const val speed = 2
            const val required_food = 50.0
        }

        object Buffalo {
            const val weight = 700.0
            const val maximum_on_cell = 10
            const val speed = 3
            const val required_food = 100.0
        }

        object Duck {
            const val weight = 1.0
            const val maximum_on_cell = 200
            const val speed = 4
            const val required_food = 0.15
        }

        object Caterpillar {
            const val weight = 0.01
            const val maximum_on_cell = 1000
            const val speed = 0
            const val required_food = 0.0
        }
    }

    object PlantCharacteristics {
        object Plant {
            const val weight = 1
            const val maximum_on_cell = 200
        }
    }
} 