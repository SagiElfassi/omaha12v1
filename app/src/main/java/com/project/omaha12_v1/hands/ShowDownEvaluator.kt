package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard


interface ShowDownEvaluator {

    fun evaluate(communityCards: Array<PokerCard>, omahaHand: OmahaHand): PokerHand
}


class ShowDownEvaluatorImpl : ShowDownEvaluator {
    override fun evaluate(communityCards: Array<PokerCard>, omahaHand: OmahaHand): PokerHand {
        return getAllHandsPermotation(communityCards, omahaHand).sortedWith(CompareHands).first()
    }

    private fun getAllHandsPermotation(hand: Array<PokerCard>, omahaHand: OmahaHand): List<PokerHand> {
        return emptyList()
    }

}

class CompareHands {
    companion object : Comparator<PokerHand> {
        override fun compare(a: PokerHand, b: PokerHand): Int = a.compare(b)
    }
}