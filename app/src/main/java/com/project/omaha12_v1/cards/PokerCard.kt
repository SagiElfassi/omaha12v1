package com.project.omaha12_v1.cards

data class PokerCard(val shape: Shape, val value: Int) {
    companion object {
        fun fromString(s: String): PokerCard? {
            if(s.length != 2) return null

            val cardValue = when (s.first()) {
                'A' -> 1
                'T' -> 10
                'J' -> 11
                'Q' -> 12
                'K' -> 13
                else -> s.first() - '0'
            }

            val cardShape = when (s[1]) {
                'd' -> Shape.DIAMOND
                'c' -> Shape.CLUB
                's' -> Shape.SPADE
                'h' -> Shape.HEART
                else -> null
            }

            return PokerCard(cardShape!!, cardValue)
        }
    }

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