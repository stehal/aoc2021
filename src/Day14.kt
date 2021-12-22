data class Element(val value: Char) {
    var valueAndInserted = mutableListOf(value)

    override fun toString(): String {
        return value.toString()
    }

}

val inputTemplate: String = "ONSVVHNCFVBHKVPCHCPV"
val testInputTemplate: String = "NNCB"


fun main() {

    fun insert(polymer: MutableList<Element>, rules: Map<Pair<Element, Element>, Element>): List<Element> {
        polymer.windowed(2).map { ee ->
            val pair = Pair(ee[0], ee[1])
            rules[pair]?.let { ee[0].valueAndInserted.add(it.value) }
        }
        return polymer.map { it.valueAndInserted }.flatten().map { Element(it) }
    }

    fun part1(input: List<String>, template: String): Int {
        var polymer = template.toCharArray().map { Element(it) }
        val rules = mutableMapOf<Pair<Element, Element>, Element>()
        input.forEach {
            rules[Pair(
                Element(it.substringBefore(" -> ").toCharArray()[0]),
                Element(it.substringBefore(" -> ").toCharArray()[1])
            )] =
                Element(it.substringAfter(" -> ").toCharArray()[0])
        }

        (0 until 10).forEach {
            polymer = insert(polymer.toMutableList(), rules)
        }
        val counts = polymer.groupingBy { it }.eachCount().values
        return counts.maxOf { it } - counts.minOf { it }

    }

    fun part2(input: List<String>, template: String): Int {
        //for each rule perform insert 10 times.
        // On the resulting polymer count elements and count pairs
        // Use these counts to calculate final result!
        return 0

    }

    val testInput = readInput("Day14_test")
    check(part1(testInput, testInputTemplate) == 1588)

    val input = readInput("Day14")
    println("part 1 ${part1(input, inputTemplate)}")
    println("part 2 ${part2(input, inputTemplate)}")
}
