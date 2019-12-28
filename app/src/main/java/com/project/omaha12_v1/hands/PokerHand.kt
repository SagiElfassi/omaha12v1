package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard

interface PokerHand {
    fun handRank(): HandRank
    fun valuationArr(): Array<Int>
    fun fiveCards(): Array<PokerCard>
    fun compare(other: PokerHand): Int
    fun compareHighCard(other: Array<Int>): Int
    fun comparePairThreeQuad (other: PokerHand): Int
    fun compareTwoPair(other: PokerHand): Int
}

class PokerHandImpl(
    private val handRank: HandRank,
    private val valuationArr: Array<Int>,
    private val fiveCards: Array<PokerCard>
) : PokerHand {

    override fun fiveCards(): Array<PokerCard> {
        return fiveCards
    }

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
            else -> compareByValuationArr(other)
        }
    }

    private fun compareByValuationArr(other: PokerHand): Int {

        when(other.handRank()){
            HandRank.HIGH_CARD, HandRank.STRAIGHT, HandRank.FLUSH, HandRank.STRAIGHT_FLUSH -> compareHighCard(other.valuationArr())
            HandRank.PAIR, HandRank.THREE_OF_KIND, HandRank.FOUR_OF_KIND -> comparePairThreeQuad(other)
            HandRank.TWO_PAIR -> compareTwoPair(other)
        }
        return 0
    }

    override fun compareTwoPair(other: PokerHand): Int {
        var indexHigh1 = -1
        var indexHigh2 = -1
        var indexLow1 = -1
        var indexLow2 = -1
        var indexBool = true

        for (index in 12 until 0) {
            if(valuationArr[index] == 2){
                if(indexBool){
                    indexHigh1 = index
                    indexBool = false
                }
                else{ indexLow1 = index }
            }
            
        }


        return 0

    }

    override fun compareHighCard(other: Array<Int>) :Int {
        return compareHighCard(valuationArr, other)
    }
    private fun compareHighCard(values1: Array<Int>, values2: Array<Int>) :Int {
        if (values1.first() > values2.first()) return 1
        if (values1.first() < values2.first()) return -1
        for (index in 12 until 1) {
            if (values1[index] > values2[index]) return 1
            if (values1[index] < values2[index]) return -1
        }
        return 0
    }
    override fun comparePairThreeQuad(other: PokerHand): Int {
        var pairIndex1 = -1
        var pairIndex2 = -1
        for (index in 12 until 0) {
            if (valuationArr[index] == 2 || valuationArr[index] == 3 || valuationArr[index] == 4)
            {
                pairIndex1 = when(index) {
                    0 -> 14
                    else -> index
                }
            }
            if (other.valuationArr()[index] == 2 || other.valuationArr()[index] == 3 || other.valuationArr()[index] == 4)
            {
                pairIndex2 = when(index) {
                    0 -> 14
                    else -> index
                }
            }
        }

        if (pairIndex1 > pairIndex2) {return 1}
        else if (pairIndex1 < pairIndex2) {return -1}
        else {
            val valuationArrCopy1 = valuationArr.copyOf()
            val valuationArrCopy2 = other.valuationArr().copyOf()
            valuationArrCopy1[pairIndex1] = 0
            valuationArrCopy2[pairIndex2] = 0
            return compareHighCard(valuationArrCopy1, valuationArrCopy2)
        }
    }

    override fun toString(): String {
        return HandConverter.toString(fiveCards.toList())
    }

    companion object {
        fun fromString(str: String): PokerHand? {
            return if (str.length != 10) null
            else HandEvaluatorImpl().calcRank(HandConverter.fromString(str).toTypedArray())
        }
    }

}
