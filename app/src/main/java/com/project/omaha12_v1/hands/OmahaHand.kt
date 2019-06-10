package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard

data class OmahaHand(val cards: List<PokerCard>) {

    companion object {
        fun fromString(str: String): OmahaHand? {
            return if(str.length != 8) null
            else OmahaHand(HandConverter.fromString(str))
        }
    }

    override fun toString(): String {
        return HandConverter.toString(cards)
    }
}