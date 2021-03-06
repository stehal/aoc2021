fun main() {
    fun part1(input: List<String>): Int {
        return input.map(String::toInt).zipWithNext().sumOf { if (it.second > it.first) 1.toInt() else 0 }
    }

    fun part2(input: List<String>): Int {
        val numbers = input.map(String::toInt)
        return numbers.zip(numbers.subList(3, numbers.lastIndex + 1))
            .sumOf { if (it.second > it.first) 1.toInt() else 0 }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
