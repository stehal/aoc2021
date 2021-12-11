fun main() {

    fun reduce(line: String): String {
        val newLine = line.replace("()", "").replace("[]", "").replace("{}", "").replace("<>", "")
        return if (newLine == line) line else reduce(newLine)
    }

    val regex = """\[\)|<\)|\{\)|\(]|<]|\{]|\(}|<}|\[}|\(>|\{>|\[>""".toRegex()
    fun part1(input: List<String>): Int {

        return input.map { reduce(it) }.map { rline -> regex.find(rline)?.value }.filterNotNull().map {
            when (it!!.last().toString()) {
                ")" -> 3
                "]" -> 57
                "}" -> 1197
                else -> 25137
            }
        }.sum()
    }

    fun calc(n: List<Int>): Long {
        var runningTotal = 0L
        n.forEach {
            runningTotal = runningTotal * 5L + it
        }
        return runningTotal
    }

    fun part2(input: List<String>): Long {
        val result = input.map { reduce(it) }.filter { rline -> !regex.containsMatchIn(rline) }.map {
            it.toCharArray().map { it.toString() }.map {
                when (it) {
                    "(" -> 1
                    "[" -> 2
                    "{" -> 3
                    else -> 4
                }

            }.reversed()
        }.map { calc(it) }.sorted()
        return result[result.size / 2]
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
