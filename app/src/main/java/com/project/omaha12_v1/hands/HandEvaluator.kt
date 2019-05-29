package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.Shape
import com.project.omaha12_v1.hands.HandRank.*

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
            isRoyalStraightOrFlush(cards = sameShapeCards)?.let { return PokerHandImpl(it, sameShapeCards) }
        }

        return PokerHandImpl(giveRankWhenNotFlushed(valueArr), valueArr)
    }


    private fun giveRankWhenNotFlushed(cards: Array<Int>): HandRank {
        val freeCards = cards.filter { i -> i == 1 }.sum()
        val hasPair = cards.any { i -> i == 2 }
        val hasThreeOfaKind = cards.any { i -> i == 3 }

        return when {
            hasPair && freeCards == 3                      -> PAIR
            hasPair && freeCards == 1                      -> TWO_PAIR
            hasThreeOfaKind && !hasPair                    -> THREE_OF_KIND
            isStraight(cards)                              -> STRAIGHT
            hasThreeOfaKind && hasPair                     -> FULL_HOUSE
            !hasThreeOfaKind && !hasPair && freeCards == 1 -> FOUR_OF_KIND
            else                                           -> HIGH_CARD
        }
    }

    private fun isRoyalStraightOrFlush(cards: Array<Int>): HandRank? {
        if(cards.sum() == 5) {
           return when {
               isRoyalStraight(cards) -> ROYAL_FLUSH
               isStraight(cards)      -> STRAIGHT_FLUSH
               else                   -> FLUSH
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