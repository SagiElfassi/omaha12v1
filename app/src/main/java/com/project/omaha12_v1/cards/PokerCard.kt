package com.project.omaha12_v1.cards

data class PokerCard(val shape: Shape, val value: Int)

enum class Shape {
    HEART, DIAMOND, CLUB, SPADE
}