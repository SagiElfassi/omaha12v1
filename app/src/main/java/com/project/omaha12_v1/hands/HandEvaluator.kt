package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.Shape

interface HandEvaluator {

     fun calcRank(other: hand):  EvaluatorImpl
}

class EvaluatorImpl (val rank: Rankings, val value: Int) : HandEvaluator {

    enum class Rankings {
        HIGHCARD, PAIR, TWOPAIR, THREEOFKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFKIND, STRAIGHTFLUSH, ROYALFLUSH
    }

    override fun calcRank(thisHand: hand): EvaluatorImpl {
        val temp  = EvaluatorImpl(Rankings.HIGHCARD, 1)

        val diamonds = IntArray(14) { i -> 0 }
        val clubs = IntArray(14) { i -> 0 }
        val spades = IntArray(14) { i -> 0 }
        val hearts = IntArray(14) { i -> 0 }

        for (card in thisHand.cards) {
            when (card.shape) {
                Shape.DIAMOND -> diamonds[card.value] += 1
                Shape.CLUB -> clubs[card.value] += 1
                Shape.SPADE -> spades[card.value] += 1
                Shape.HEART -> hearts[card.value] += 1
            }
        }

        return temp

    }
}