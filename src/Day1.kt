import java.io.File

fun main(args: Array<String>) {
    println(Day1.part1(File("src/day1.txt").readLines()))
    println(Day1.part2(File("src/day1.txt").readLines()))
}

object Day1 {
    fun part1(numbers: List<String>) = numbers.map { it.toInt() }.sum().toString()

    fun part2(numbers: List<String>): String {
        val freq = mutableListOf(0)
        var value = 0
        while(true) {
            numbers.map {
                value += it.toInt()
                if (freq.contains(value)) {
                    return value.toString()
                }
                freq.add(value)
            }
        }
    }
}


