fun main() {

    fun neighbours(cave: List<List<Int>>, x: Int, y: Int): List<Pair<Int, Int>> {
        return listOf(
            Pair(x + 1, y),
            Pair(x - 1, y),
            Pair(x, y + 1),
            Pair(x, y - 1)
        ).filter { it.first >= 0 && it.first < cave[0].size && it.second >= 0 && it.second < cave.size }
    }

    fun isLow(cave: List<List<Int>>, x: Int, y: Int): Boolean {
        return cave[y][x] < neighbours(cave, x, y).minOf { cave[it.second][it.first] }
    }

    fun part1(input: List<String>): Int {
        val cave = input.map { line ->
            line.toCharArray().map { Character.getNumericValue(it) }.toList()
        }
        return (cave.indices).sumOf { y ->
            (cave[0].indices).sumOf { x ->
                if (isLow(cave, x, y)
                ) cave[y][x] + 1 else 0
            }
        }
    }

    fun basin(cave: List<List<Int>>, neighbours: List<Pair<Int, Int>>, basin: MutableSet<Pair<Int, Int>>): Set<Pair<Int, Int>> {
        neighbours.forEach { neighbour ->
            if (cave[neighbour.second][neighbour.first] != 9 && !basin.contains(neighbour)) {
                basin.add(neighbour)
                basin(cave, neighbours(cave, neighbour.first, neighbour.second), basin)
            }
        }
        return basin
    }

    fun part2(input: List<String>): Int {
        val cave = input.map { line ->
            line.toCharArray().map { Character.getNumericValue(it) }.toList()
        }
        val lowPoints = (cave.indices).map { y ->
            (cave[0].indices).map { x ->
                Pair(x, y)
            }.filter { isLow(cave, it.first, it.second) }
        }.flatten()

        val basins = lowPoints.map {
            basin(cave, neighbours(cave, it.first, it.second), mutableSetOf<Pair<Int, Int>>())
        }
        return basins.sortedByDescending { it.size }.subList(0, 3).map { it.size }.reduce { acc, i -> acc * i }
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
