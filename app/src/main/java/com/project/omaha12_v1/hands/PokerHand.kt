package com.project.omaha12_v1.hands

interface PokerHand {
    fun handRank(): HandRank
    fun valuationArr(): Array<Int>
    fun compare(other: PokerHand)
}

class PokerHandImpl(private val handRank: HandRank, val valuationArr: Array<Int>): PokerHand {
    override fun valuationArr(): Array<Int> {
        return valuationArr
    }

    override fun handRank(): HandRank {
        return handRank
    }

    override fun compare(other: PokerHand) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}