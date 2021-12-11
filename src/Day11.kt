fun main() {

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1)

    val input = readInput("Day11")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
