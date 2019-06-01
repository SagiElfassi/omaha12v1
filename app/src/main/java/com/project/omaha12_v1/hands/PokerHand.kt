package com.project.omaha12_v1.hands

interface PokerHand {
    fun handRank(): HandRank
    fun valuationArr(): Array<Int>
    fun compare(other: PokerHand): Int
}

class PokerHandImpl(private val handRank: HandRank, private val valuationArr: Array<Int>) : PokerHand {

    override fun valuationArr(): Array<Int> {
        return valuationArr
    }

    override fun handRank(): HandRank {
        return handRank
    }

    override fun compare(other: PokerHand): Int {
        return if (this.handRank > other.handRank()) 1
        else -1
    }
}
