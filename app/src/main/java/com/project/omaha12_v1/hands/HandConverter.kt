package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard

object HandConverter {

    fun fromString(str: String): List<PokerCard> {
        return fromStringRec(ArrayList(), str)
    }

    private fun fromStringRec(acc: List<PokerCard>, str: String): List<PokerCard> {
        return if(str.isEmpty()) acc
        else fromStringRec(acc.plus(PokerCard.fromString(str.take(2))!!) ,str.removeRange(0,2))
    }

    fun toString(cards: List<PokerCard>): String {
        return cards.map { card -> card.toString() }.toString()
    }
}