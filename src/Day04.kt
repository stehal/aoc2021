class Board() {
    var value: MutableList<MutableList<Number>> = mutableListOf()

    fun addRow(row: String): Unit {
        val regex = "\\s+".toRegex()
        value.add(row.trim().split(regex).map { Number(it.toInt()) }.toMutableList())
    }

    fun mark(drawn: Int) {
        value.forEach { row ->
            row.forEach {
                if (it.value == drawn) it.marked = true
            }
        }
    }

    fun sumOfUnmarked(): Int {
        return value.map { row -> row.filter { number -> !number.marked }.sumOf { number -> number.value } }
            .sumOf { it }
    }

    fun aRowWins(): Boolean {
        value.forEach { if (rowWins(it)) return true }
        return false
    }

    private fun columnWins(col: Int): Boolean {
        value.forEach { if (!it[col].marked) return false }
        return true
    }

    fun aColumnWins(): Boolean {
        (0 until 5).forEach { if (columnWins(it)) return true }
        return false
    }

    fun rowWins(row: List<Number>): Boolean {
        row.forEach { if (!it.marked) return false }
        return true
    }

    fun wins(): Boolean {
        if (aColumnWins()) return true
        if (aRowWins()) return true
        return false
    }
}

data class Number(val value: Int) {
    var marked: Boolean = false
}

fun main() {
    fun boards(input: List<String>): List<Board> {
        val boards = mutableListOf<Board>()
        input.subList(2, input.size).windowed(5, 6).forEach {
            val b = Board()
            it.forEach {
                b.addRow(it)
            }
            boards.add(b)
        }
        return boards
    }
    fun part1(input: List<String>): Int {
        val draws = input[0].split(",").map { it.toInt() }.toList()
        val boards = boards(input)

        draws.forEach { draw ->
            boards.forEach { board ->
                board.mark(draw)
                if (board.wins()) {
                    return board.sumOfUnmarked() * draw
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        var order = 0
        val draws = input[0].split(",").map { it.toInt() }.toList()
        val boards = boards(input)

        draws.forEach { draw ->
            boards.forEach { board ->
                if (!board.wins()) {
                    board.mark(draw)
                    if (board.wins()) {
                        order += 1
                        if (order == boards.size) {
                            return board.sumOfUnmarked() * draw
                        }
                    }
                }
            }
        }
        return 0
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println("part 1 ${part1(input)}")
    println("part 2 ${part2(input)}")
}
