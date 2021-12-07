import kotlin.math.abs

data class Result(val candidate: Int, val distance: Int)

fun main() {

    fun part1(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }

        return (positions.minOf { it }..positions.maxOf { it }).map { candidate ->
            Result(
                candidate,
                positions.sumOf { abs(candidate - it) }
            )
        }
            .minOf { it.distance }
    }

    fun fuel(start: Int, finish: Int): Int {
        val dist = abs(start - finish)
        return dist * (dist + 1) / 2
    }

    fun part2(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        return (positions.minOf { it }..positions.maxOf { it }).map { candidate ->
            Result(
                candidate,
                positions.sumOf { fuel(candidate, it) }
            )
        }.minOf { it.distance }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)
    val input = readInput("Day07")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
