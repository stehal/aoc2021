data class Element(val value: Char) {
    var valueAndInserted = mutableListOf(value)

    override fun toString(): String {
        return value.toString()
    }
}

const val inputTemplate: String = "ONSVVHNCFVBHKVPCHCPV"
const val testInputTemplate: String = "NNCB"

fun main() {

    fun insert(polymer: MutableList<Element>, rules: Map<Pair<Element, Element>, Element>): List<Element> {
        polymer.windowed(2).map { ee ->
            val pair = Pair(ee[0], ee[1])
            rules[pair]?.let { ee[0].valueAndInserted.add(it.value) }
        }
        return polymer.map { it.valueAndInserted }.flatten().map { Element(it) }
    }

    fun rules(input: List<String>): Map<Pair<Element, Element>, Element> {
        val rules = mutableMapOf<Pair<Element, Element>, Element>()
        input.forEach {
            rules[Pair(
                Element(it.substringBefore(" -> ").toCharArray()[0]),
                Element(it.substringBefore(" -> ").toCharArray()[1])
            )] =
                Element(it.substringAfter(" -> ").toCharArray()[0])
        }
        return rules
    }

    fun polymer(template: String, rules: Map<Pair<Element, Element>, Element>): List<Element> {
        var polymer = template.toCharArray().map { Element(it) }
        (0 until 10).forEach { _ ->
            polymer = insert(polymer.toMutableList(), rules)
        }
        return polymer
    }

    fun part1(input: List<String>, template: String): Int {
        val polymer = polymer(template, rules(input))
        val counts = polymer.groupingBy { it }.eachCount().values
        return counts.maxOf { it } - counts.minOf { it }
    }


    fun round(before: Map<Pair<Element, Element>, Long>, rules: Map<Pair<Element, Element>, Map<List<Element>,Int>>): Map<Pair<Element, Element>, Long> {
        val after = mutableMapOf<Pair<Element, Element>, Long>()
        before.forEach { p ->
            rules[p.key]?.forEach {
                val k = Pair(it.key[0], it.key[1])
                after.merge(k, p.value * it.value, Long::plus)
            }
        }
        return after
    }

    fun round(before: Map<Pair<Element, Element>, Int>, rules: Map<Pair<Element, Element>, Map<List<Element>,Int>>): Map<Pair<Element, Element>, Long> {
        val longBefore  = mutableMapOf<Pair<Element, Element>, Long>()
        before.forEach{
            longBefore[it.key] = it.value.toLong()
        }
        return round(longBefore,rules)
    }

    fun part2(input: List<String>, template: String): Long {
        val rules = rules(input)
        val r = mutableMapOf<Pair<Element, Element>, Map<List<Element>, Int>>()
        rules(input).keys.forEach { rule ->
            r[rule] = polymer("${rule.first}${rule.second}", rules).windowed(2).groupingBy { it }.eachCount()
        }

        val start = template.windowed(2).groupingBy { Pair(Element(it.toCharArray()[0]), Element(it.toCharArray()[1])) }
            .eachCount()
        var after = round(start, r)
        after = round(after, r)
        after = round(after, r)
        after = round(after, r)

        val total = mutableMapOf<Element, Long>()
        after.forEach {
            total.merge(it.key.first, it.value, Long::plus)
            total.merge(it.key.second, it.value, Long::plus)
        }

        var max = total.values.maxOf { it }
        if (max % 2 == 1L) max += 1L

        var min = total.values.minOf { it }
        if (min % 2 == 1L) min += 1L

        return max / 2L - min / 2L
    }

    val testInput = readInput("Day14_test")
    check(part1(testInput, testInputTemplate) == 1588)

    val input = readInput("Day14")
    println("part 1 ${part1(input, inputTemplate)}")
    check(part2(testInput, testInputTemplate) == 2188189693529)
    println("part 2 ${part2(input, inputTemplate)}")
}
