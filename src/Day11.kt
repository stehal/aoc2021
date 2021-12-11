data class Octopus(var energy: Int, var hasFlashed: Boolean = false) {

    fun isAboutToFlash(): Boolean {
        return !hasFlashed && energy > 9
    }

    fun increment() {
        energy++
    }

    fun reset() {
        energy = 0
        hasFlashed = false
    }

}

fun adjacent(cave: List<List<Octopus>>, origin: Pair<Int, Int>): List<Octopus> {
    return (-1..+1).map { x -> (-1..+1).map { y -> Pair(origin.first + x, origin.second + y) } }.flatten()
        .filter {
            it.first >= 0 && it.first < cave.size && it.second >= 0 && it.second < cave.size && origin != Pair(
                it.first,
                it.second
            )
        }.map { cave[it.second][it.first] }
}

fun incrementEnergy(cave: List<List<Octopus>>) {
    (cave.indices).map { y -> (cave.indices).map { x -> cave[y][x].increment() } }
}

fun flashes(cave: List<List<Octopus>>): Int {
    return (cave.indices).sumOf { y -> (cave.indices).count { x -> cave[y][x].hasFlashed } }
}

fun flash(cave: List<List<Octopus>>): Int {
    val numberOfFlashed = flashes(cave)
    (cave.indices).map { y ->
        (cave.indices).map { x ->
            if (cave[y][x].isAboutToFlash()) {
                cave[y][x].hasFlashed = true
                adjacent(cave, Pair(x, y)).forEach { octopus -> octopus.increment() }
            }
        }
    }
    return if (numberOfFlashed == flashes(cave)) flashes(cave) else flash(cave)
}

fun reset(cave: List<List<Octopus>>) {
    (cave.indices).map { y ->
        (cave.indices).forEach { x ->
            if (cave[y][x].hasFlashed) cave[y][x].reset()
        }
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        var totalFlashes = 0
        val cave = input.map { line ->
            line.toCharArray().map { Octopus(Character.getNumericValue(it)) }.toList()
        }

        (1..100).forEach {
            incrementEnergy(cave)
            totalFlashes += flash(cave)
            reset(cave)
        }
        return totalFlashes
    }

    fun part2(input: List<String>): Int {
        val cave = input.map { line ->
            line.toCharArray().map { Octopus(Character.getNumericValue(it)) }.toList()
        }
        var count = 1
        while (true) {
            incrementEnergy(cave)
            if (flash(cave) == 100) return count
            reset(cave)
            count++
        }
    }

    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
