import java.io.File
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println(Day7.part1(File("src/day7.txt").readLines()))
    //println(Day7.part2(File("src/day7.txt").readLines(), 10000))
}

object Day7 {

    fun part1(steps: List<String>): String {
        var result = ""
        val map = HashMap<String, TreeNode<String>>()

        steps.map {
            val splits = it.split(" ")
            val value = splits[1]
            val child = splits[7]
            if (!map.containsKey(value)) {
                map[value] = TreeNode(value)
            }
            if (!map.containsKey(child)) {
                map[child] = TreeNode(child)
            }

            map[value]?.addChild(map[child]!!)
        }

        val rootList = mutableListOf<String>()

        map.map {
            val node = it.value
            if(node.parent.size == 0) rootList.add(node.value)
        }
        rootList.sort()


        val availableSteps = sortedSetOf<String>()
        availableSteps.addAll(rootList)

        while(result.length < map.size) {
            val step = availableSteps.first()
            val node = map[step]!!
            node.used = true
            result += step
            availableSteps.remove(step)

            node.children.map { it ->
                var add = true
                it.parent.map { parent ->
                    add = add && parent.used
                }
                if (add && !it.used) availableSteps.add(it.value)
            }
        }
        return result
    }

    class TreeNode<T>(var value: T){
        var parent:MutableList<TreeNode<T>> = mutableListOf()
        var used = false
        var children:MutableList<TreeNode<T>> = mutableListOf()

        fun addChild(node:TreeNode<T>){
            children.add(node)
            node.parent.add(this)
        }
    }
}
