package com.project.omaha12_v1.cards

data class PokerCard(val shape: Shape, val value: Int) {

    override fun toString(): String {
        val cardValue = when (value) {
            1 -> "A"
            10 -> "T"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> "$value"
        }

        val cardShape = when (shape) {
            Shape.DIAMOND -> "d"
            Shape.CLUB -> "c"
            Shape.SPADE -> "s"
            Shape.HEART -> "h"
        }

        return cardValue + cardShape

    }

}

enum class Shape {
    HEART, DIAMOND, CLUB, SPADE
}