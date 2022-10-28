package org.example.adventofcode.puzzle

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day02Test {

    @Test
    fun part1() {
        assertEquals(1234, Day02.part1("/day02.txt"), "Should be 1234")
    }

    @Test
    fun part2() {
        assertEquals(35345, Day02.part2("/day02.txt"))
    }
}