import java.io.File

fun main(args: Array<String>) {
    println(Day3.part1(File("src/day3.txt").readLines()))
    println(Day3.part2(File("src/day3.txt").readLines()))
}

object Day3 {

    fun part1(ids: List<String>): String {

        val matrix = Array(1000) {IntArray(1000)}

        var count = 0
        ids.map {
            val claim = it.getClaim()

            for(i in claim.top..(claim.top+claim.height-1)) {
                for(j in claim.left..(claim.left+claim.width-1)) {
                    if(matrix[i][j] == 0) {
                        matrix[i][j] = claim.id
                    } else {
                        if (matrix[i][j] != -1) count++
                        matrix[i][j] = -1
                    }
                }
            }
        }
        return count.toString()
    }

    fun part2(ids: List<String>): String {

        val matrix = Array(1000) {IntArray(1000)}

        ids.map {
            val claim = it.getClaim()

            for(i in claim.top..(claim.top+claim.height-1)) {
                for(j in claim.left..(claim.left+claim.width-1)) {
                    if(matrix[i][j] == 0) {
                        matrix[i][j] = claim.id
                    } else {
                        matrix[i][j] = -1
                    }
                }
            }
        }

        ids.map {
            val claim = it.getClaim()
            val total = claim.height * claim.width
            var count = 0

            for(i in 0 until 1000) {
                for (j in 0 until 1000) {
                    if (matrix[i][j] == claim.id) count++
                }
            }
            if (count == total) return claim.id.toString()
        }

        return "ERROR"
    }

    private fun String.getClaim(): Claim {
        val claims = split(" ")
        val pads = claims[2].split(",")
        val size = claims[3].split("x")
        val id = claims[0].removePrefix("#").toInt()
        return Claim(
            id,
            pads.first().toInt(),
            pads.last().removeSuffix(":").toInt(),
            size.first().toInt(),
            size.last().toInt()
        )
    }

    class Claim(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int)

}


