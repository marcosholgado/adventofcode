
import kotlin.test.assertEquals

import org.junit.jupiter.api.Test

internal class Day3Test {

    @Test
    fun testDay3() {
        val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        val out = Day3.part1(input)
        assertEquals("4", out)
    }

    @Test
    fun testDay31() {
        val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        val out = Day3.part1(input)
        assertEquals("3", out)
    }
}