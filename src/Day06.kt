data class Lanternfish(var timer: Int)

fun main() {

    fun part1(input: List<String>): Int {
        val school = input[0].split(",").map { Lanternfish(it.toInt()) }.toMutableList()
        for (day in (1..80)) {
            val births = school.filter { it.timer == 0 }.map { it.timer = 7 }
            births.forEach { school.add(Lanternfish(9)) }
            school.map { lanternfish ->
                lanternfish.timer -= 1
            }
        }
        return school.size
    }

    fun part2(input: List<String>): Long {
        val school = HashMap<Int, Long>()
        (0..9).forEach { school[it] = 0 }

        input[0].split(",").map { it.toInt() }.forEach { it -> school[it] = school[it]!! + 1.toLong() }

        (1..256).forEach { _ ->
            school[9] = school[0]!!
            school[7] = school[7]!! + school[0]!!
            school[0] = 0
            (1..9).forEach {
                school[it - 1] = school[it]!!
                school[it] = 0
            }
        }
        return school.values.sumOf { it }
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)

    val input = readInput("Day06")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}

