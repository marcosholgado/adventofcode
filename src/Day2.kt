import java.io.File

fun main(args: Array<String>) {
    println(Day2.part1(File("src/day2.txt").readLines()))
    println(Day2.part2(File("src/day2.txt").readLines()))
}

object Day2 {

    fun part1(ids: List<String>): String {
        var rep2 = 0
        var rep3 = 0

        ids.map { id ->
            val res = id.groupingBy { it }.eachCount()
            if (res.containsValue(2)) rep2++
            if (res.containsValue(3)) rep3++
        }

        return (rep2 * rep3).toString()
    }

    fun part2(ids: List<String>): String {

        val len = ids.first().length

        for (i in 0..len) {
            val newIds = ids.map { it.removeRange(i, i+1) }
            val results = newIds.groupingBy { it }.eachCount()
            if (results.containsValue(2)) {
                return results.filter { it.value == 2 }.toString()
            }
        }
        return "Error"
    }

}


