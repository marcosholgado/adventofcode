
import kotlin.test.assertEquals

import org.junit.jupiter.api.Test

internal class Day5Test {

    @Test
    fun testDay5() {
        val input = "dabAcCaCBAcCcaDA"
        val out = Day5.part1(input)
        assertEquals("10", out)
    }

    @Test
    fun testDay52() {
        val input = "dabAcCaCBAcCcaDA"
        val out = Day5.part2(input)
        assertEquals("4", out)
    }


}