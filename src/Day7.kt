
import java.io.File
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println(Day7.part1(File("src/day7.txt").readLines()))
    println(Day7.part2(File("src/day7.txt").readLines(), 5, 60))
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

    fun part2(steps: List<String>, numWorkers: Int, dur: Int): String {
        var result = ""
        var seconds = -1
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
        val availableSteps = sortedSetOf<String>()
        val workers = mutableListOf<Worker>()

        map.map {
            val node = it.value
            if(node.parent.size == 0) rootList.add(node.value)
        }
        rootList.sort()

        availableSteps.addAll(rootList)

        for(i in 0 until numWorkers) {
            workers.add(Worker(0, null))
        }

        var workerAvailable: Int
        while(result.length < map.size) {
            seconds++

            workers.map {
                it.seconds--
                if (it.seconds == 0) {
                    result += it.node!!.value
                    val node = map[it.node!!.value]!!
                    node.finished = true
                    it.node?.children?.map { child ->
                        var add = true
                        child.parent.map { parent ->
                            add = add && parent.used && parent.finished
                        }
                        if (add && !child.used) availableSteps.add(child.value)
                    }
                }
            }

            workerAvailable = workers.indexOfFirst { it.seconds <= 0 }
            while(workerAvailable != -1 && availableSteps.size > 0) {
                val step = availableSteps.first()
                val node = map[step]!!
                workers[workerAvailable].seconds = step.single().toInt() + dur - 64
                workers[workerAvailable].node = node
                node.used = true
                availableSteps.remove(step)
                workerAvailable = workers.indexOfFirst { it.seconds <= 0 }
            }
        }
        return seconds.toString()
    }

    data class Worker(var seconds: Int, var node: TreeNode<String>?)

    class TreeNode<T>(var value: T){
        var parent:MutableList<TreeNode<T>> = mutableListOf()
        var used = false
        var finished = false
        var children:MutableList<TreeNode<T>> = mutableListOf()

        fun addChild(node:TreeNode<T>){
            children.add(node)
            node.parent.add(this)
        }
    }
}
