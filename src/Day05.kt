import kotlin.math.*

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point) {
    fun isHorizontalOrVertical(): Boolean {
        return (isHorizontal() || isVertical())
    }

    private fun isHorizontal(): Boolean {
        return (start.x == end.x)
    }

    private fun isVertical(): Boolean {
        return (start.y == end.y)
    }

    private fun horizontalPassesThrough(): List<Point> {
        return List<Int>(1 + abs(end.y - start.y)) { start.x }.zip(
            min(start.y, end.y)..max(
                start.y,
                end.y
            )
        ) { x, y -> Point(x, y) }
    }

    private fun verticalPassesThrough(): List<Point> {
        return (min(start.x, end.x)..max(
            start.x,
            end.x
        )).zip(List<Int>(1 + abs(end.x - start.x)) { start.y }) { x, y -> Point(x, y) }
    }

    private fun diagonalPassesThrough(): List<Point> {
        var xRange = (min(start.x, end.x)..max(start.x, end.x)).toList()
        if (start.x > end.x) xRange = xRange.asReversed()

        var yRange = (min(start.y, end.y)..max(start.y, end.y)).toList()
        if (start.y > end.y) yRange = yRange.asReversed()
        return xRange.zip(yRange) { x, y -> Point(x, y) }
    }

    fun passesThrough(): List<Point> {
        if (isHorizontal()) return horizontalPassesThrough()
        if (isVertical()) return verticalPassesThrough()
        return diagonalPassesThrough()
    }
}

data class Diagram(val lines: List<Line>) {
    fun onlyhv(): List<Line> {
        return lines.filter { it.isHorizontalOrVertical() }
    }
}

fun parse(inputLine: String): Line {
    val (x1, y1, x2, y2) = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)+").find(inputLine)!!.destructured
    return Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
}

fun main() {

    fun part1(input: List<String>): Int {
        return Diagram(input.map { parse(it) }).onlyhv().flatMap { it.passesThrough() }.groupingBy { it }.eachCount()
            .filter { it.value > 1 }.size
    }

    fun part2(input: List<String>): Int {
        return Diagram(input.map { parse(it) }).lines.flatMap { it.passesThrough() }.groupingBy { it }.eachCount()
            .filter { it.value > 1 }.size
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
