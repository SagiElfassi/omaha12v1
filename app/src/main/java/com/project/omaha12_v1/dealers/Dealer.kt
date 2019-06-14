package com.project.omaha12_v1.dealers

import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.hands.CompareHands
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.PokerHand
import com.project.omaha12_v1.hands.ShowDownEvaluator
import com.project.omaha12_v1.players.Player

interface Dealer {
    fun deal(players: List<Player>)

    fun shuffle()

    fun open3Flops(): List<List<PokerCard>>

    fun openTurnAndRiver(): List<List<PokerCard>>

    fun getDeck(): CardDeck

    fun calcBestHand(communityCards: Array<PokerCard>, omahaHands: List<OmahaHand>): Pair<PokerHand, OmahaHand>
}

class DealerImpl(
    private val cardDeck: CardDeck,
    private val showDownEvaluator: ShowDownEvaluator) : Dealer {

    override fun calcBestHand(communityCards: Array<PokerCard>, omahaHands: List<OmahaHand>): Pair<PokerHand, OmahaHand> {
        val resultMap = omahaHands.map { omahaHand -> Pair(showDownEvaluator.evaluate(communityCards, omahaHand), omahaHand) }
            .toMap().toSortedMap(CompareHands)

        return Pair(resultMap.firstKey(), resultMap[resultMap.firstKey()]!!)
    }

    override fun getDeck(): CardDeck {
        return cardDeck
    }

    override fun open3Flops(): List<List<PokerCard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openTurnAndRiver(): List<List<PokerCard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deal(players: List<Player>) {
        players.forEach { player ->
            player.takeCards((1 until 12)
                .map { cardDeck.takeCard() })
        }
    }

    override fun shuffle() {
        return cardDeck.shuffle()
    }
}