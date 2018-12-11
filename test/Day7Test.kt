
import kotlin.test.assertEquals

import org.junit.jupiter.api.Test

internal class Day7Test {

    @Test
    fun testDay7() {
        val input = listOf(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
        )
        val out = Day7.part1(input)
        assertEquals("CABDFE", out)
    }
}