package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard

interface Hand {

    fun value(): Int
    fun compare(other: Hand)

}

class hand(val cards: Array<PokerCard>): Hand{
    override fun value(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun compare(other: Hand) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}