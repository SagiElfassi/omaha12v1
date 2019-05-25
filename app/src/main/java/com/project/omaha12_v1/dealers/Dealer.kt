package com.project.omaha12_v1.dealers

import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.players.Player

interface Dealer {
    fun deal(players: Sequence<Player>): Int

    fun shuffle(): Unit

    fun open3Flops(): List<List<PokerCard>>

    fun openTurnAndRiver(): List<List<PokerCard>>

    fun getDeck(): CardDeck
}

class DealerImpl(private val cardDeck: CardDeck) : Dealer {

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