import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        val noOfInputs = input.size
        val lengthOfEachInput = input[0].length
        val gamma: MutableList<String> = MutableList(lengthOfEachInput) { "0" }
        val b = MutableList(lengthOfEachInput) { 0 }

        input.forEach { inputValue ->
            (0 until lengthOfEachInput).forEach { b[it] += inputValue[it].toString().toInt() }
        }

        (0 until lengthOfEachInput).forEach { if (b[it] > noOfInputs / 2) gamma.add("1") else gamma.add("0") }

        val g = gamma.joinToString("").toInt(2)
        val e = g.inv() + 2.toDouble().pow(lengthOfEachInput)
        return g * e.toInt()
    }

    fun part2(input: List<String>): Int {
        var ogr = input.toList()
        var csr = input.toList()
        (0 until input[0].length).forEach {
            ogr = filter(ogr, it) { b, size -> b >= size / 2.0 }
            csr = filter(csr, it) { b, size -> b < size / 2.0 }
        }

        return ogr[0].toInt(2) * csr[0].toInt(2)
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}

fun filter(input: List<String>, pos: Int, isOne: (b: Int, size: Int) -> Boolean): List<String> {
    if (input.size == 1) return input
    var b = 0
    input.forEach { inputValue -> b += inputValue[pos].toString().toInt() }
    return input.filter {
        it[pos].toString() == if (isOne(b, input.size)) "1" else "0"
    }
}