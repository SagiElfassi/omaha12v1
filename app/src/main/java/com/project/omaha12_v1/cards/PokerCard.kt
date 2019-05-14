package com.project.omaha12_v1.cards

data class PokerCard(val shape: Shape, val value: Int)

enum class Shape {
    HEARTS, DIAMOND, CLUB, SPADE
}