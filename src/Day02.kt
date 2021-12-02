fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var position = 0
        input.map { it.split(" ") }.forEach {
            when (it[0]) {
                "forward" -> position += it[1].toInt()
                "down" -> depth += it[1].toInt()
                "up" -> depth -= it[1].toInt()
            }
        }
        return depth * position
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var position = 0
        var aim = 0
        input.map { it.split(" ") }.forEach {
            when (it[0]) {
                "forward" -> {
                    position += it[1].toInt()
                    depth += aim* it[1].toInt()
                }
                "down" -> aim += it[1].toInt()
                "up" -> aim -= it[1].toInt()
            }
        }
        return depth * position
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
