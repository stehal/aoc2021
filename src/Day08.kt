fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf {line ->
            line.split("|")[1].trim().split(" ").map {
                when (it.length) {
                    2 -> 1
                    4 -> 1
                    3 -> 1
                    7 -> 1
                    else -> 0
                }
            }.sum()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            var patterns = line.split("|")[0].trim().split(" ").map {
                it.toCharArray().toSet()
            }.toSet()
            val output = line.split("|")[1].trim().split(" ").map {
                it.toCharArray().toSet()
            }.toList()
            val r = MutableList(10) { setOf<Char>() }

            r[1] = patterns.first { it.size == 2 }
            patterns = patterns.minus(listOf(r[1]).toSet())
            r[4] = patterns.first { it.size == 4 }
            patterns = patterns.minus(listOf(r[4]).toSet())
            r[7] = patterns.first { it.size == 3 }
            patterns = patterns.minus(listOf(r[7]).toSet())
            r[8] = patterns.first { it.size == 7 }
            patterns = patterns.minus(listOf(r[8]).toSet())
            r[9] = patterns.filter { it.intersect(r[4]) == r[4] }.first()
            patterns = patterns.minus(listOf(r[9]).toSet())
            r[0] = patterns.filter { it.size == 6 && it.intersect(r[1]) == r[1] }.first()
            patterns = patterns.minus(listOf(r[0]).toSet())
            r[6] = patterns.filter { it.size == 6 }.first()
            patterns = patterns.minus(listOf(r[6]).toSet())
            r[5] = patterns.filter { it.intersect(r[6]) == it }.first()
            patterns = patterns.minus(listOf(r[5]).toSet())
            r[3] = patterns.filter { it.intersect(r[1]) == r[1] }.first()
            patterns = patterns.minus(listOf(r[3]).toSet())
            r[2] = patterns.first()

            output.joinToString(separator = "") { r.indexOf(it).toString() }.toInt()
        }

    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
