import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day04BoardTest {

    private fun testBoard(): Board {
        val board = Board()
        board.addRow("1 2 3 4 5")
        board.addRow("6 7 8 9 10")
        board.addRow("11 12 13 14 15")
        board.addRow("16 17 18 19 20")
        board.addRow("21 22 23 24 25")
        return board
    }

    @Test
    fun addRow() {
        val board = Board()
        board.addRow("11 12 13 14 15")
        assertEquals(Number(11), board.value[0][0])
        board.addRow(" 5 12 13 14 15")
        assertEquals(Number(5), board.value[1][0])
        board.addRow(" 5 12 13  4 15")
        assertEquals(Number(4), board.value[2][3])
    }

    @Test
    fun mark() {
        val board = testBoard()
        board.mark(12)
        assertEquals(false, board.value[0][0].marked)
        assertEquals(true, board.value[2][1].marked)
    }

    @Test
    fun sumOf() {
        val board = testBoard()
        board.mark(12)
        board.mark(19)
        assertEquals(294, board.sumOfUnmarked())
    }

    @Test
    fun rowWins() {
        val board = Board()
        val a = Number(1)
        val b = Number(1)
        val c = Number(1)
        val d = Number(1)
        val e = Number(1)

        assertFalse(board.rowWins(listOf(a, b, c, d, e)))
        a.marked = true
        assertFalse(board.rowWins(listOf(a, b, c, d, e)))
        b.marked = true
        c.marked = true
        d.marked = true
        e.marked = true
        assertTrue(board.rowWins(listOf(a, b, c, d, e)))
        b.marked = true
    }

    @Test
    fun aRowWins() {
        val board = testBoard()
        assertEquals(false, board.aRowWins())
        board.mark(11)
        board.mark(12)
        board.mark(13)
        board.mark(14)
        board.mark(15)
        assertTrue(board.aRowWins())
    }

    @Test
    fun aColWins() {
        val board = testBoard()
        assertEquals(false, board.aColumnWins())
        board.mark(2)
        board.mark(7)
        board.mark(12)
        board.mark(17)
        board.mark(22)
        assertTrue(board.aColumnWins())
    }

    @Test
    fun aBoardWinsWithRow() {
        val board = testBoard()
        board.mark(11)
        board.mark(12)
        board.mark(13)
        board.mark(14)
        board.mark(15)
        assertTrue(board.hasWon())
    }

    @Test
    fun aBoardWins() {
        val board = testBoard()
        assertEquals(false, board.hasWon())
        board.mark(2)
        board.mark(7)
        board.mark(12)
        board.mark(17)
        board.mark(22)
        assertTrue(board.hasWon())
    }
}
