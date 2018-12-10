
import kotlin.test.assertEquals

import org.junit.jupiter.api.Test

internal class Day1Test {

    @Test
    fun testDay1() {
        val input = listOf("+1", "-2", "+3", "+1")
        val out = Day1.part1(input)
        assertEquals("3", out)
    }

    @Test
    fun testDay2() {
        val input = listOf("+1", "+1", "+1")
        val out = Day1.part1(input)
        assertEquals("3", out)
    }

    @Test
    fun testDay3() {
        val input = listOf("+1", "+1", "-2")
        val out = Day1.part1(input)
        assertEquals("0", out)
    }

    @Test
    fun testDay4() {
        val input = listOf("-1", "-2", "-3")
        val out = Day1.part1(input)
        assertEquals("-6", out)
    }

    @Test
    fun testDay5() {
        val input = listOf("+1", "-2", "+3", "+1")
        val out = Day1.part2(input)
        assertEquals("2", out)
    }

    @Test
    fun testDay6() {
        val input = listOf("+1", "-1")
        val out = Day1.part2(input)
        assertEquals("0", out)
    }

    @Test
    fun testDay7() {
        val input = listOf("+3", "+3", "+4", "-2", "-4")
        val out = Day1.part2(input)
        assertEquals("10", out)
    }

    @Test
    fun testDay8() {
        val input = listOf("-6", "+3", "+8", "+5", "-6")
        val out = Day1.part2(input)
        assertEquals("5", out)
    }

    @Test
    fun testDay9() {
        val input = listOf("+7", "+7", "-2", "-7", "-4")
        val out = Day1.part2(input)
        assertEquals("14", out)
    }
}