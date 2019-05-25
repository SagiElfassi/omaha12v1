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

        return PokerHandImpl(HandRank.PAIR, valueArr)

    }
}