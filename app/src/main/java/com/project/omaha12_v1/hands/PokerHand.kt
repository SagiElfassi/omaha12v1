package com.project.omaha12_v1.hands

interface PokerHand {
    fun handRank(): HandRank
    fun valuationArr(): Array<Int>
    fun compare(other: PokerHand): Int
}

class PokerHandImpl(private val handRank: HandRank,
                    private val valuationArr: Array<Int>) : PokerHand {

    override fun valuationArr(): Array<Int> {
        return valuationArr
    }

    override fun handRank(): HandRank {
        return handRank
    }

    override fun compare(other: PokerHand): Int {
        return when {
            handRank > other.handRank() -> 1
            handRank < other.handRank() -> -1
            else -> compareByValuationArr(other.valuationArr())
        }
    }

    private fun compareByValuationArr(otherValuationArr: Array<Int>): Int {
        if (valuationArr.first() > otherValuationArr.first()) return 1
        if (valuationArr.first() < otherValuationArr.first()) return -1
        for (index in 12 until 1) {
            if (valuationArr[index] > otherValuationArr[index]) return 1
            if (valuationArr[index] < otherValuationArr[index]) return -1
        }
        return 0
    }
}
