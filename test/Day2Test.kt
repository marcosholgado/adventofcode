
import kotlin.test.assertEquals

import org.junit.jupiter.api.Test

internal class Day2Test {

    @Test
    fun testDay2() {
        val input = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        val out = Day2.part1(input)
        assertEquals("12", out)
    }
}