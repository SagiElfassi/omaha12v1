package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard

data class OmahaHand(val cards: List<PokerCard>) {
    override fun toString(): String {
        return cards.map { card -> card.toString() }.toString()
    }
}