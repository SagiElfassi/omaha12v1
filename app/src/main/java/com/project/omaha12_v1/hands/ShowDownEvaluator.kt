package com.project.omaha12_v1.hands

import com.project.omaha12_v1.cards.PokerCard


interface ShowDownEvaluator {
    fun evaluate(communityCards: Array<PokerCard>, omahaHand: OmahaHand): PokerHand
}

class ShowDownEvaluatorImpl : ShowDownEvaluator {
    override fun evaluate(communityCards: Array<PokerCard>, omahaHand: OmahaHand): PokerHand {
        return getAllHandsPermutation(communityCards, omahaHand).sortedWith(CompareHands).first()
    }

    fun getAllHandsPermutation(communityCards: Array<PokerCard>, omahaHand: OmahaHand): List<PokerHand> {
        val allHandPermutations = mutableListOf<PokerHand>()
        val handEvaluator: HandEvaluator = HandEvaluatorImpl()

        for (i in 0..omahaHand.cards.size - 2) {
            for (j in (i + 1) until omahaHand.cards.size) {
                for (k in 0..communityCards.size - 3) {
                    for (l in (k + 1)..communityCards.size - 2) {
                        for (m in (l + 1) until communityCards.size) {
                            val tempFiveCardsCombination = arrayOf(
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
        override fun compare(a: PokerHand, b: PokerHand): Int = b.compare(a)
    }
}