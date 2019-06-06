package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard

data class OmahaHand(val cards: List<PokerCard>) {
    companion object {
        fun fromString(str: String): OmahaHand? {
            if(str.length != 8) return null
            return OmahaHand(fromStringRec(ArrayList(), str))
        }

        private fun fromStringRec(acc: List<PokerCard>, str: String): List<PokerCard> {
            return if(str.isEmpty()) acc
            else fromStringRec(acc.plus(PokerCard.fromString(str.take(2))!!) ,str.removeRange(0,2))
        }
    }

    override fun toString(): String {
        return cards.map { card -> card.toString() }.toString()
    }
}