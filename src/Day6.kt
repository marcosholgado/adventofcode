import java.io.File

fun main(args: Array<String>) {
    println(Day6.part1(File("src/day6.txt").readLines()))
    println(Day6.part2(File("src/day6.txt").readLines(), 10000))
}

object Day6 {

    fun part1(coords: List<String>): String {
        val points = mutableListOf<Point>()
        val finalPoints = mutableListOf<Boolean>()

        coords.map {
            points.add(it.getPoint())
            finalPoints.add(true)
        }

        val right = points.maxBy { it.x }!!.x + 1
        val bottom = points.maxBy { it.y }!!.y + 1
        val matrix = Array(right) {IntArray(bottom)}

        points.map {
            matrix[it.x][it.y] = points.indexOf(it) + 1
        }

        for(i in 0 until right) {
            for (j in 0 until bottom) {
                val point = Point(i, j)
                if (matrix[i][j] == 0) {

                    var min = Int.MAX_VALUE
                    var indexPoint = 0
                    points.map {
                        val dist = point.manhattanDistanceTo(it)
                        if (dist == min) {
                            indexPoint = -1
                        }
                        if (dist < min) {
                            min = dist
                            indexPoint = points.indexOf(it) + 1
                        }
                    }
                    matrix[i][j] = indexPoint

                    if (indexPoint > 0 && (i == 0 || j == 0 || i == right || j == bottom)) {
                        finalPoints[indexPoint-1] = false
                    }
                }
            }
        }

        val list = IntArray(points.size + 1)

        for(i in 0 until right) {
            for (j in 0 until bottom) {
                val value = matrix[i][j]
                if (value > 0 && finalPoints[value-1]) list[value]++
            }
        }

        return list.maxBy { it }.toString()
    }

    fun part2(coords: List<String>, limit: Int): String {
        val points = mutableListOf<Point>()
        val finalPoints = mutableListOf<Boolean>()
        var count = 0

        coords.map {
            points.add(it.getPoint())
            finalPoints.add(true)
        }

        val right = points.maxBy { it.x }!!.x + 1
        val bottom = points.maxBy { it.y }!!.y + 1
        val matrix = Array(right) {IntArray(bottom)}

        points.map {
            matrix[it.x][it.y] = points.indexOf(it) + 1
        }

        for(i in 0 until right) {
            for (j in 0 until bottom) {
                val point = Point(i, j)
                var dist = 0
                points.map {
                    dist += point.manhattanDistanceTo(it)
                }

                if (dist < limit) {
                    count++
                }
            }
        }

        return count.toString()
    }

    private fun String.getPoint(): Point {
        val values = split(",")
        val x = values.first().toInt()
        val y = values.last().trim().toInt()
        return Point(x, y)
    }

    class Point(val x: Int, val y: Int) {
        fun manhattanDistanceTo(that: Point): Int = Math.abs(x - that.x) + Math.abs(y - that.y)
    }

}


