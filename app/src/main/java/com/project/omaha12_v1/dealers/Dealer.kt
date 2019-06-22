package com.project.omaha12_v1.dealers

import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.hands.ShowDownEvaluator
import com.project.omaha12_v1.players.Player
import com.project.omaha12_v1.players.PlayerOmahaHand
import com.project.omaha12_v1.players.BestHand

interface Dealer {
    fun deal(players: List<Player>)

    fun shuffle()

    fun open3Flops(): List<List<PokerCard>>

    fun openTurnAndRiver(): List<List<PokerCard>>

    fun getDeck(): CardDeck

    fun calcBestHand(communityCards: Array<PokerCard>, playerOmahaHands: List<PlayerOmahaHand>): List<BestHand>
}

class DealerImpl(
    private var cardDeck: CardDeck,
    private val showDownEvaluator: ShowDownEvaluator
) : Dealer {

    override fun calcBestHand(communityCards: Array<PokerCard>, playerOmahaHands: List<PlayerOmahaHand>): List<BestHand>{

        val winners = playerOmahaHands.map { playerOmahaHand ->
            BestHand(
                playerOmahaHand.playerId,
                showDownEvaluator.evaluate(communityCards, playerOmahaHand.omahaHand)
            ) }.sortedWith(ComparePlayerHand)

        return winners.filter { player -> ComparePlayerHand.compare(player, winners.first()) == 0 }
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
            player.takeCards((1 until 13)
                .map { cardDeck.takeCard() })
        }
    }

    override fun shuffle() {
        cardDeck = cardDeck.shuffle()
    }
}

class ComparePlayerHand {
    companion object : Comparator<BestHand> {
        override fun compare(a: BestHand, b: BestHand): Int = b.pokerHand.compare(a.pokerHand)
    }
}