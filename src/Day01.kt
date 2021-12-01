fun main() {
    fun part1(input: List<String>): Int {
        var count = 0;
        input.map(String::toInt).zipWithNext().forEach { if (it.first < it.second) count++ }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0;
        val numbers = input.map(String::toInt)
        numbers.zip(numbers.subList(1, numbers.lastIndex + 1)).zip(numbers.subList(2, numbers.lastIndex + 1))
            .map { it.first.first + it.first.second + it.second }.zipWithNext()
            .forEach { if (it.first < it.second) count++ }
        return count
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
