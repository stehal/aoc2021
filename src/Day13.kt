data class Dot(val x: Int, val y: Int)
data class Fold(val axis: String, val position: Int)

fun main() {

    fun foldX(dots: Set<Dot>, position: Int): Set<Dot> {
        return dots.map { dot -> if (dot.x < position) dot else Dot(2 * position - dot.x, dot.y) }.toSet()
    }

    fun foldY(dots: Set<Dot>, position: Int): Set<Dot> {
        return dots.map { dot -> if (dot.y < position) dot else Dot(dot.x, 2 * position - dot.y) }.toSet()
    }

    fun fold(dots: Set<Dot>, fold: Fold): Set<Dot> {
        return if (fold.axis == "x") foldX(dots, fold.position) else foldY(dots, fold.position)
    }

    fun foldAll(dots: Set<Dot>, folds: List<Fold>): Set<Dot> {
        var folded = dots.toSet()
        folds.forEach {
            folded = fold(folded, it)
        }
        return folded
    }

    fun collectDots(input: List<String>): Set<Dot> {
        return input.filter { it.contains(",") }
            .map { Dot(it.substringBefore(",").toInt(), it.substringAfter(",").toInt()) }.toSet()
    }

    fun collectFolds(input: List<String>): List<Fold> {
        return input.filter { it.contains("=") }
            .map { Fold(it.substringBefore("=").last().toString(), it.substringAfter("=").toInt()) }
    }

    fun part1(input: List<String>): Int {
        return fold(collectDots(input), collectFolds(input)[0]).size
    }

    fun printMap(dots: Set<Dot>) {
        val maxx = dots.maxOf { it.x }
        val maxy = dots.maxOf { it.y }
        (0..maxy).forEach { y ->
            (0..maxx).forEach { x ->
                if (dots.contains(Dot(x, y))) print("#") else print(".")
            }
            println()
        }
    }

    fun part2(input: List<String>): Set<Dot> {
        return foldAll(collectDots(input), collectFolds(input))
    }

    val testInput = readInput("Day13_test")
    check(part1(testInput) == 17)

    val input = readInput("Day13")
    println("part 1 ${part1(input)}")
    println("part 2 test")
    printMap(part2(testInput))
    println("part 2")
    printMap(part2(input))
}
