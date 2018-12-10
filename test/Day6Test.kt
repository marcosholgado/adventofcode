
import kotlin.test.assertEquals

import org.junit.jupiter.api.Test

internal class Day6Test {

    @Test
    fun testDay6() {
        val input = listOf("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9")
        val out = Day6.part1(input)
        assertEquals("17", out)
    }



}