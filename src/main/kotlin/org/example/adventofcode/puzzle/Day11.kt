package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader
import kotlin.math.floor

object Day11 {

    fun part1(filePath: String): Int {
        val lines = FileLoader.loadFromFile<String>(filePath)
        var monkeys = loadMonkeys(lines)
        monkeys = findMonkeyActivity(monkeys, 20)
        return calculateMonkeyBusiness(monkeys)
    }

    fun part2(filePath: String): Int {
        val lines = FileLoader.loadFromFile<String>(filePath)
        return 1
    }

    fun findMonkeyActivity(monkeys: ArrayList<Monkey>, rounds: Int): ArrayList<Monkey> {
        for (roundNumber in 0 until rounds) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    var currentItemWorry = item
                    // Inspect
                    monkey.monkeyActivity += 1
                    currentItemWorry = evaluateExpression(monkey.operation!!, currentItemWorry)

                    // Lessen Worry
                    currentItemWorry = floor(currentItemWorry.toFloat()/3f).toInt()

                    // Test
                    if(currentItemWorry % monkey.testDivisor!! == 0) {
                        monkeys[monkey.testPassMonkey!!].items.add(currentItemWorry)
                    } else {
                        monkeys[monkey.testFailMonkey!!].items.add(currentItemWorry)
                    }

                }
                monkey.items.clear()
            }
        }

        return monkeys
    }

    fun calculateMonkeyBusiness(monkeyActivity: List<Monkey>): Int {
        val sortedList = monkeyActivity.sortedByDescending { it.monkeyActivity }
        return sortedList[0].monkeyActivity * sortedList[1].monkeyActivity
    }

    fun evaluateExpression(expression: String, old: Int): Int {
        val replacedString = expression.replace("old", old.toString())
        val expressionParts = replacedString.split(" ")
        val operator = expressionParts[3]
        val number1 = expressionParts[2].toInt()
        val number2 = expressionParts[4].toInt()

        return when(operator) {
            "+" -> number1+number2
            "-" -> number1-number2
            "*" -> number1*number2
            "/" -> number1/number2
            else -> -1
        }
    }

    fun loadMonkeys(lines: List<String>): ArrayList<Monkey> {
        val monkeyList = ArrayList<Monkey>()

        var currentMonkey = Monkey()
        for (line in lines) {
            if(line.startsWith("Monkey")) {
                currentMonkey = Monkey()
                val monkeyNumber = line.split(" ", ":")[1]
                currentMonkey.monkeyNumber = monkeyNumber.toInt()
            } else if(line.contains("Starting items")) {
                val startingItemsStrings = line.split(":")[1].trim().split(",")
                val startingItemList = startingItemsStrings.map { it.trim().toInt() }
                val startingItemArrayList = ArrayList<Int>()
                startingItemArrayList.addAll(startingItemList)
                currentMonkey.items = startingItemArrayList
            } else if(line.contains("Operation")) {
                currentMonkey.operation = line.split(":")[1].trim()
            } else if(line.contains("Test")) {
                currentMonkey.testDivisor = line.split("by")[1].trim().toInt()
            } else if(line.contains("true")) {
                currentMonkey.testPassMonkey = line.split("monkey")[1].trim().toInt()
            } else if(line.contains("false")) {
                currentMonkey.testFailMonkey = line.split("monkey")[1].trim().toInt()
            } else if(line.isBlank()) {
                monkeyList.add(currentMonkey)
            }
        }

        monkeyList.add(currentMonkey)

        return monkeyList
    }

    data class Monkey(
        var monkeyNumber: Int? = null,
        var items: ArrayList<Int> = ArrayList(),
        var testDivisor: Int? = null,
        var testPassMonkey: Int? = null,
        var testFailMonkey: Int? = null,
        var operation: String? = null,
        var monkeyActivity: Int = 0
    )
}

fun main() {
    println("Part 1 example solution is: ${Day11.part1("/day11_example.txt")}")
    println("Part 1 main solution is: ${Day11.part1("/day11.txt")}")
    println("Part 2 example solution is: ${Day11.part2("/day11_example.txt")}")
    println("Part 2 main solution is: ${Day11.part2("/day11.txt")}")
}