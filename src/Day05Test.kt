import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day05Test {

    @Test
    fun parse() {
        assertEquals(Point(1, 1), parse("1,1 -> 1,3").start)
        assertEquals(Point(1, 3), parse("1,1 -> 1,3").end)
    }

    @Test
    fun horizontalPassesThrough() {
        assertEquals(listOf(Point(1, 1), Point(1, 2), Point(1, 3)), parse("1,1 -> 1,3").passesThrough())
    }

    @Test
    fun verticalPassesThrough() {
        println(parse("3,1 -> 1,1").passesThrough())
        assertEquals(listOf(Point(1, 1), Point(2, 1), Point(3, 1)), parse("3,1 -> 1,1").passesThrough())
    }

    @Test
    fun diagonalPassesThrough() {
        assertEquals(listOf(Point(1, 1), Point(2, 2), Point(3, 3)), parse("1,1 -> 3,3").passesThrough())
        assertEquals(listOf(Point(9, 7), Point(8, 8), Point(7, 9)), parse("9,7 -> 7,9").passesThrough())
    }
}
