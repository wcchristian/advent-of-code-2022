package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader

object Day01 {
    fun printSolution() {
        println("Example 1: ${part1("/day01_example.txt")}")
        println("Solution 1: ${part1("/day01.txt")}")
        println("Example 2: ${part2("/day01_example.txt")}")
        println("Solution 2: ${part2("/day01.txt")}")
    }

    fun part1(filePath: String): String {
        val stringLines = FileLoader.loadFromFile<String>(filePath)
        return stringLines[0]
    }

    fun part2(filePath: String): String {
        val sampleLines = FileLoader.loadFromFile<String>(filePath)
        return sampleLines[1]
    }
}