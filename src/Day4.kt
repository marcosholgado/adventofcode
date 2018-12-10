import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println(Day4.part1(File("src/day4.txt").readLines()))
    println(Day4.part2(File("src/day4.txt").readLines()))
}

object Day4 {

    fun part1(ids: List<String>): String {
        val map: HashMap<Int, IntArray> = getMap(ids)

        val g = map.maxBy { it.value.sum() }!!.key
        val v = map[g]!!.indexOf(map[g]!!.maxBy { it }!!)
        return (g * v).toString()
    }

    fun part2(ids: List<String>): String {
        val map: HashMap<Int, IntArray> = getMap(ids)
        val map2: HashMap<Int, Int> = HashMap()

        map.map { map2[it.key] = it.value.max()!! }

        val g = map2.maxBy { it.value }!!.key
        val v = map[g]!!.indexOf(map[g]!!.maxBy { it }!!)

        return (g * v).toString()
    }

    private fun getMap(ids: List<String>): HashMap<Int, IntArray> {
        val registers = mutableListOf<Register>()
        val map: HashMap<Int, IntArray> = HashMap()

        ids.map {
            registers.add(it.getRegister())
        }
        val set = registers.toSortedSet()
        var prevGuard = 0
        var prevAwake = true
        var prevMin = 0

        set.map {
            if (it.guard != 0) {
                prevGuard = it.guard
                if (!map.containsKey(it.guard)) {
                    val array = IntArray(60) { 0 }
                    map[it.guard] = array
                }
            } else {
                if (!prevAwake && it.awake) {
                    for (i in prevMin until it.date.minute) {
                        map[prevGuard]!![i]++
                    }
                }
                prevMin = it.date.minute
                prevAwake = it.awake
            }
        }
        return map
    }


    private fun String.getRegister(): Register{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
        val date = LocalDateTime.parse(substring(1, 17), formatter)
        val state = substring(19,20)
        var guard = 0
        val awake = when(state) {
            "f" -> false
            else -> true
        }
        if (state == "G") {
            guard = split(" ")[3].removePrefix("#").toInt()
        }

        return Register(
            date,
            guard,
            awake
        )
    }

    class Register(val date: LocalDateTime, val guard: Int, val awake: Boolean) : Comparable<Register>{
        override fun compareTo(other: Register) = when {
            date.isAfter(other.date) -> 1
            date.isBefore(other.date) -> -1
            else -> 0
        }
    }
}


