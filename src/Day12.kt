data class Cave(val name: String) {
    fun isSmall(): Boolean {
        return name.lowercase() == name
    }

    fun isEnd(): Boolean {
        return name.lowercase() == "end"
    }

    override fun toString() :String{
        return name
    }
}

var count = 0

var caves: MutableMap<Cave, MutableSet<Cave>> = mutableMapOf()

fun walk(from: Cave, path: MutableList<Cave>) {
    path.add(from)
    if (from.isEnd()) {
        count += 1
    }

    caves[from]?.filter { !it.isSmall() || !path.contains(it) }?.forEach {
        var newPath = path.toMutableList()
        walk(it, newPath)
    }
    return
}

fun canAdd(path: MutableList<Cave>, cave: Cave): Boolean {
    if (cave.name == "start") return false
    if (! cave.isSmall()) return true
    if (!path.filter{it.isSmall()}.groupingBy { it }.eachCount().map { it.value }.contains(2)) return true
    return !path.contains(cave)

}

fun walk2(from: Cave, path: MutableList<Cave>) {
    path.add(from)

    if (from.isEnd()) {
        count += 1
        return
    }

    caves[from]?.filter {canAdd(path, it)}?.forEach {
        val newPath = path.toMutableList()
        walk2(it, newPath)
    }
    return
}

fun main() {

    fun setup(input: List<String>) {
        count = 0
        caves = mutableMapOf()
        input.forEach {
            val c1 = Cave(it.substringBefore("-"))
            val c2 = Cave(it.substringAfter("-"))
            val connectionsFromC1 = caves[c1] ?: mutableSetOf()
            connectionsFromC1.add(c2)
            caves[c1] = connectionsFromC1
            val connectionsFromC2 = caves[c2] ?: mutableSetOf()
            connectionsFromC2.add(c1)
            caves[c2] = connectionsFromC2
        }
    }

    fun part1(input: List<String>): Int {
        setup(input)
        walk(Cave("start"), mutableListOf())
        return count
    }

    fun part2(input: List<String>): Int {
        setup(input)
        walk2(Cave("start"), mutableListOf())
        return count
    }

    val testInput = readInput("Day12_test")
    check(part1(testInput) == 10) { "part 1 small" }
    val testInputLarger = readInput("Day12_slightly_larger_test")
    check(part1(testInputLarger) == 19) { "part 1 slightly larger" }
    val testInputLargest = readInput("Day12_even_larger_test")
    check(part1(testInputLargest) == 226) { "part 1 largest" }
    val input = readInput("Day12")
    println("part 1 ${part1(input)}")
    check(part2(testInput) == 36) { "part 2 small" }
    println("part 2 ${part2(input)}")
}
