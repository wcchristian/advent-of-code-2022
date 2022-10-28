package org.example.adventofcode.practice2021

import org.example.adventofcode.util.FileLoader

object PracticeDay1 {
    fun printSolution() {
        println("Example 1: ${part1("/practiceDay01_example.txt")}")
        println("Solution 1: ${part1("/practiceDay01.txt")}")
        println("Example 2: ${part2("/practiceDay01_example.txt")}")
        println("Solution 2: ${part2("/practiceDay01.txt")}")
    }

    fun part1(filePath: String): Int {
        val intLines = FileLoader.loadFromFile<Int>(filePath)
        var timesIncreased = 0

        for (i in intLines.indices) {
            if (i != 0 && intLines[i] > intLines[i-1]) { // Increased
                timesIncreased += 1
            }
        }

        return timesIncreased
    }

    fun part2(filePath: String): Int {
        val intLines = FileLoader.loadFromFile<Int>(filePath)
        val windowList = ArrayList<Int>()
        var timesIncreased = 0

        for (i in 1 until intLines.size-1) {
            windowList.add(intLines[i-1] + intLines[i] + intLines[i+1])
        }

        for (i in windowList.indices) {
            if (i != 0 && windowList[i] > windowList[i-1]) { // Increased
                timesIncreased += 1
            }
        }

        return timesIncreased
    }
}