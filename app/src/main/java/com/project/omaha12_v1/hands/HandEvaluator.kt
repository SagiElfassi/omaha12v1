package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.Shape

interface HandEvaluator {

    fun calcRank(hand: Array<PokerCard>): PokerHand
}

class HandEvaluatorImpl : HandEvaluator {

    override fun calcRank(hand: Array<PokerCard>): PokerHand {
        val diamonds = IntArray(13) { 0 }
        val clubs = IntArray(13) { 0 }
        val spades = IntArray(13) { 0 }
        val hearts = IntArray(13) { 0 }

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

        when(isRoyalStraightOrFlush(diamonds)){
            1 -> return PokerHandImpl(HandRank.FLUSH, valueArr)
            2 -> return PokerHandImpl(HandRank.STRAIGHT_FLUSH, valueArr)
            3 -> return PokerHandImpl(HandRank.ROYAL_FLUSH, valueArr)
        }
        when(isRoyalStraightOrFlush(clubs)){
            1 -> return PokerHandImpl(HandRank.FLUSH, valueArr)
            2 -> return PokerHandImpl(HandRank.STRAIGHT_FLUSH, valueArr)
            3 -> return PokerHandImpl(HandRank.ROYAL_FLUSH, valueArr)
        }
        when(isRoyalStraightOrFlush(spades)){
            1 -> return PokerHandImpl(HandRank.FLUSH, valueArr)
            2 -> return PokerHandImpl(HandRank.STRAIGHT_FLUSH, valueArr)
            3 -> return PokerHandImpl(HandRank.ROYAL_FLUSH, valueArr)
        }
        when(isRoyalStraightOrFlush(hearts)){
            1 -> return PokerHandImpl(HandRank.FLUSH, valueArr)
            2 -> return PokerHandImpl(HandRank.STRAIGHT_FLUSH, valueArr)
            3 -> return PokerHandImpl(HandRank.ROYAL_FLUSH, valueArr)
        }

        return PokerHandImpl(HandRank.PAIR, valueArr)
    }

    private fun isRoyalStraightOrFlush(cards: IntArray): Int {
        var result = 0
        if (cards.sum() == 5) result += 1
        if (isStraight(cards) && result == 1) result += 1
        if (isRoyalStraight(cards) && result == 1) result += 2
        return result
    }

    private fun isStraight(cards: IntArray): Boolean{
        return false
    }

    private fun isRoyalStraight(cards: IntArray): Boolean{
        if (cards[0] == 1 && cards[12] == 1 && cards[11] == 1 && cards[10] == 1 && cards[9] == 1) return true
        return false
    }
}