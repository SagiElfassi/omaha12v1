package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.Shape

interface HandEvaluator {

    fun calcRank(hand: Array<PokerCard>): PokerHand
}

class HandEvaluatorImpl : HandEvaluator {

    override fun calcRank(hand: Array<PokerCard>): PokerHand {
        val diamonds = Array(13) { 0 }
        val clubs    = Array(13) { 0 }
        val spades   = Array(13) { 0 }
        val hearts   = Array(13) { 0 }

        for (card in hand) {
            when (card.shape) {
                Shape.DIAMOND -> diamonds[card.value - 1] += 1
                Shape.CLUB    -> clubs[card.value - 1]    += 1
                Shape.SPADE   -> spades[card.value - 1]   += 1
                Shape.HEART   -> hearts[card.value - 1]   += 1
            }
        }

        val valueArr = Array(13) { i ->
            diamonds[i] + clubs[i] + spades[i] + hearts[i]
        }

        val allShapes = arrayOf(diamonds, clubs, spades, hearts)

        for (sameShapeCards in allShapes) {
            isRoyalStraightOrFlush(cards = sameShapeCards)?.let { return it }
        }

        return giveRankWhenNotFlushed(valueArr)
    }


    private fun giveRankWhenNotFlushed(cards: Array<Int>): PokerHandImpl {
        var ones = 0
        var two = false
        var three = false

        for (value in cards) {
            if (value == 1) ones += 1
            if (value == 2) two = true
            if (value == 3) three = true
        }

        return if(two && ones == 3)            PokerHandImpl(HandRank.PAIR, cards)
        else   if(two && ones == 1)            PokerHandImpl(HandRank.TWO_PAIR, cards)
        else   if(three && !two)               PokerHandImpl(HandRank.THREE_OF_KIND, cards)
        else   if(isStraight(cards))           PokerHandImpl(HandRank.STRAIGHT, cards)
        else   if(three && two)                PokerHandImpl(HandRank.FULL_HOUSE, cards)
        else   if(!three && !two && ones == 1) PokerHandImpl(HandRank.FOUR_OF_KIND, cards)
        else                                   PokerHandImpl(HandRank.HIGH_CARD, cards)
    }

    private fun isRoyalStraightOrFlush(cards: Array<Int>): PokerHand? {
        if(cards.sum() == 5) {
           return when {
               isRoyalStraight(cards) -> PokerHandImpl(HandRank.ROYAL_FLUSH, cards)
               isStraight(cards)      -> PokerHandImpl(HandRank.STRAIGHT_FLUSH, cards)
               else                   -> PokerHandImpl(HandRank.FLUSH, cards)
           }
        }
        return null
    }

    private fun isStraight(cards: Array<Int>): Boolean {
        for (x in 0 until 9) {
            if ((cards[x] == 1 && cards[x + 1] == 1 && cards[x + 2] == 1 && cards[x + 3] == 1 && cards[x + 4] == 1) || isRoyalStraight(cards))
                return true
        }
        return false
    }

    private fun isRoyalStraight(cards: Array<Int>): Boolean {
        return cards[0] == 1 && cards[12] == 1 && cards[11] == 1 && cards[10] == 1 && cards[9] == 1
    }
}