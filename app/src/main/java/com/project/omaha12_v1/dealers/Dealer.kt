package com.project.omaha12_v1.dealers

import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.players.Player
import java.util.*

interface Dealer {
    fun deal(players: Sequence<Player>): Int

    fun shuffle(deck: CardDeck): CardDeck

    fun open3Flops(): List<List<PokerCard>>

    fun openTurnAndRiver(): List<List<PokerCard>>
}

class DealerImpl : Dealer {
    override fun open3Flops(): List<List<PokerCard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openTurnAndRiver(): List<List<PokerCard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deal(players: Sequence<Player>): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shuffle(deck: CardDeck): CardDeck {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}