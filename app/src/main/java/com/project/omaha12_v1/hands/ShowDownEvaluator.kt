package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard


interface ShowDownEvaluator {

    fun evaluate(communityCards: Array<PokerCard>, omahaHand: OmahaHand): PokerHand
}


class ShowDownEvaluatorImpl : ShowDownEvaluator {
    override fun evaluate(communityCards: Array<PokerCard>, omahaHand: OmahaHand): PokerHand {
        return getAllHandsPermutation(communityCards, omahaHand).sortedWith(CompareHands).first()
    }

    private fun getAllHandsPermutation(communityCards: Array<PokerCard>, omahaHand: OmahaHand): List<PokerHand> {
        val allHandPermutations = mutableListOf<PokerHand>()
        var handEvaluator: HandEvaluator = HandEvaluatorImpl()

        for (i in 0..omahaHand.cards.size - 1) {
            for (j in (i + 1)..omahaHand.cards.size) {
                for (k in 0..communityCards.size - 2) {
                    for (l in (k + 1)..communityCards.size - 1) {
                        for (m in (l + 1)..communityCards.size) {
                            var tempFiveCardsCombination = arrayOf(
                                omahaHand.cards[i], omahaHand.cards[j],
                                communityCards[k], communityCards[l], communityCards[m]
                            )
                            allHandPermutations.add(handEvaluator.calcRank(tempFiveCardsCombination))
                        }
                    }
                }
            }
        }
        return allHandPermutations
    }
}

class CompareHands {
    companion object : Comparator<PokerHand> {
        override fun compare(a: PokerHand, b: PokerHand): Int = a.compare(b)
    }
}