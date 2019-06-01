package com.project.omaha12_v1.dealers

import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.ShowDownEvaluator
import com.project.omaha12_v1.players.Player

interface Dealer {
    fun deal(players: Sequence<Player>): Int

    fun shuffle()

    fun open3Flops(): List<List<PokerCard>>

    fun openTurnAndRiver(): List<List<PokerCard>>

    fun getDeck(): CardDeck

    fun calcBestHand(communityCards: Array<PokerCard>, omahaHands: List<OmahaHand>)
}

class DealerImpl(private val cardDeck: CardDeck,
                 val showDownEvaluator: ShowDownEvaluator
): Dealer {
    override fun calcBestHand(communityCards: Array<PokerCard>, omahaHands: List<OmahaHand>) {
        omahaHands.map { omahaHand -> showDownEvaluator.evaluate(communityCards, omahaHand) }

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

    override fun deal(players: Sequence<Player>): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shuffle() {
        return cardDeck.shuffle()
    }
}