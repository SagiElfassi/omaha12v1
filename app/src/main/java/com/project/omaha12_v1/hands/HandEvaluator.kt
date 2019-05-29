package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.Shape

interface HandEvaluator {

    fun calcRank(hand: Array<PokerCard>): PokerHand
}

class HandEvaluatorImpl : HandEvaluator {

    override fun calcRank(hand: Array<PokerCard>): PokerHand {
        val diamonds = Array<Int>(13) { 0 }
        val clubs = Array<Int>(13) { 0 }
        val spades = Array<Int>(13) { 0 }
        val hearts = Array<Int>(13) { 0 }

        for (card in hand) {
            when (card.shape) {
                Shape.DIAMOND -> diamonds[card.value - 1] += 1
                Shape.CLUB -> clubs[card.value - 1] += 1
                Shape.SPADE -> spades[card.value - 1] += 1
                Shape.HEART -> hearts[card.value - 1] += 1
            }
        }

        val valueArr = Array(13) { i ->
            diamonds[i] + clubs[i] + spades[i] + hearts[i]
        }

        val allShapes = arrayOf(diamonds, clubs, spades, hearts)

        for (sameShapeCards in allShapes) {
            when (isRoyalStraightOrFlush(sameShapeCards)) {
                1 -> return PokerHandImpl(HandRank.FLUSH, valueArr)
                2 -> return PokerHandImpl(HandRank.STRAIGHT_FLUSH, valueArr)
                3 -> return PokerHandImpl(HandRank.ROYAL_FLUSH, valueArr)
            }
        }

        when (giveRankWhenNotFlushed(valueArr)) {
            0 -> return PokerHandImpl(HandRank.PAIR, valueArr)
            1 -> return PokerHandImpl(HandRank.TWO_PAIR, valueArr)
            2 -> return PokerHandImpl(HandRank.THREE_OF_KIND, valueArr)
            3 -> return PokerHandImpl(HandRank.STRAIGHT, valueArr)
            4 -> return PokerHandImpl(HandRank.FULL_HOUSE, valueArr)
            5 -> return PokerHandImpl(HandRank.FOUR_OF_KIND, valueArr)
        }
        return PokerHandImpl(HandRank.HIGH_CARD, valueArr)
    }

    private fun giveRankWhenNotFlushed(cards: Array<Int>): Int {
        var ones = 0
        var two = false
        var three = false
        for (value in cards) {
            if (value == 1) ones += 1
            if (value == 2) two = true
            if (value == 3) three = true
        }
        if(two && ones == 3) return 0 //pair
        if(two && ones == 1) return 1 //two pairs
        if(three && !two) return 2 //three of a kind
        if(isRoyalStraight(cards) || isStraight(cards)) return 3 //straight
        if(three && two) return 4 // full house
        if(!three && !two && ones == 1) return 5

        return -1
    }

    private fun isRoyalStraightOrFlush(cards: Array<Int>): Int {
        var result = 0
        if (cards.sum() == 5) result += 1
        if (isStraight(cards)) result += 1
        if (isRoyalStraight(cards)) result += 2
        return result
    }

    private fun isStraight(cards: Array<Int>): Boolean {
        for (x in 0 until 9) {
            if (cards[x] == 1 && cards[x + 1] == 1 && cards[x + 2] == 1 && cards[x + 3] == 1 && cards[x + 4] == 1) return true
        }
        return false
    }

    private fun isRoyalStraight(cards: Array<Int>): Boolean {
        if (cards[0] == 1 && cards[12] == 1 && cards[11] == 1 && cards[10] == 1 && cards[9] == 1) return true
        return false
    }

}