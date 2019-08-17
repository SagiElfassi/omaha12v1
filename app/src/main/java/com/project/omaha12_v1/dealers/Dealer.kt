package com.project.omaha12_v1.dealers

import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.hands.HandRank
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.ShowDownEvaluator
import com.project.omaha12_v1.players.Player
import com.project.omaha12_v1.players.PlayerOmahaHand
import com.project.omaha12_v1.players.PlayerHand
import android.util.Log

interface Dealer {
    fun deal(players: List<Player>)

    fun shuffle()

    fun open3Flops(): List<List<PokerCard>>

    fun openTurnAndRiver(): List<List<PokerCard>>

    fun getDeck(): CardDeck

    fun calcBestHand(communityCards: Array<PokerCard>, playerOmahaHands: List<PlayerOmahaHand>): List<PlayerHand>

    fun calcBonus(communityCards: Array<PokerCard>, playerOmahaHand: OmahaHand): Double
}

class DealerImpl(
    private var cardDeck: CardDeck,
    private val showDownEvaluator: ShowDownEvaluator
) : Dealer {


    override fun calcBestHand(communityCards: Array<PokerCard>, playerOmahaHands: List<PlayerOmahaHand>): List<PlayerHand> {


        val playersHand = playerOmahaHands.map { playerOmahaHand ->
            PlayerHand(
                playerOmahaHand.playerId,
                showDownEvaluator.evaluate(communityCards, playerOmahaHand.omahaHand)
            )
            }.sortedWith(ComparePlayerHand)

        //playersHand.forEach { Log.d("TAG","playerHand: $it")}
        return playersHand.filter { player -> ComparePlayerHand.compare(player, playersHand.first()) == 0 }
    }

    override fun calcBonus(communityCards: Array<PokerCard>, playerOmahaHand: OmahaHand): Double {
        return when(showDownEvaluator.evaluate(communityCards, playerOmahaHand).handRank()) {
            HandRank.FOUR_OF_KIND -> 2.0
            HandRank.STRAIGHT_FLUSH -> 5.0
            HandRank.ROYAL_FLUSH -> 10.0
            else -> 0.0
        }
    }

    override fun getDeck(): CardDeck {
        return cardDeck
    }

    override fun open3Flops(): List<List<PokerCard>> {
        return listOf(
            listOf(cardDeck.takeCard(), cardDeck.takeCard(), cardDeck.takeCard()),
            listOf(cardDeck.takeCard(), cardDeck.takeCard(), cardDeck.takeCard()),
            listOf(cardDeck.takeCard(), cardDeck.takeCard(), cardDeck.takeCard())
        )
    }

    override fun openTurnAndRiver(): List<List<PokerCard>> {
        return listOf(
            listOf(cardDeck.takeCard(), cardDeck.takeCard()),
            listOf(cardDeck.takeCard(), cardDeck.takeCard()),
            listOf(cardDeck.takeCard(), cardDeck.takeCard())
        )
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
    companion object : Comparator<PlayerHand> {
        override fun compare(a: PlayerHand, b: PlayerHand): Int = b.pokerHand.compare(a.pokerHand)
    }
}