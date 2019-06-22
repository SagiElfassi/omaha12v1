package com.project.omaha12_v1.game

import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.dealers.Dealer
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.players.Player
import com.project.omaha12_v1.players.PlayerOmahaHand
import kotlin.reflect.KFunction1

class Omaha12Game(val dealer: Dealer, val gameBoard: GameBoard, val players: List<Player>) {

    fun startNewGame() {
        dealer.shuffle()
        dealer.deal(players)
    }

    fun open3Flops(): GameBoard {
        val allFlops = dealer.open3Flops()
        return gameBoard.putThreeFlops(allFlops[0], allFlops[1], allFlops[2])
    }

    fun openTurnAndRivers(): GameBoard {
        val allTurnAndRivers = dealer.openTurnAndRiver()
        return gameBoard
            .putTurnAndRiverForFirstKoo(allTurnAndRivers[0][0], allTurnAndRivers[0][1])
            .putTurnAndRiverForSecondKoo(allTurnAndRivers[1][0], allTurnAndRivers[1][1])
            .putTurnAndRiverForThirdKoo(allTurnAndRivers[2][0], allTurnAndRivers[2][1])
    }

    fun calculateResult(): GameResult {

        val firstKooResults = getKooResult(gameBoard.firstKoo(), Player::getFirstFlopCards)
        val secondKooResults = getKooResult(gameBoard.secondKoo(), Player::getSecondFlopCards)
        val thirdKooResults = getKooResult(gameBoard.thirdKoo(), Player::getThirdFlopCards)

        val gameResult = GameResult(
            listOf(firstKooResults, secondKooResults, thirdKooResults)
                .flatten()
                .groupBy { it.playerId }
                .map {
                    PlayerResult(
                        it.key,
                        it.value.map { entry -> entry.resultsPoints }.fold(0.0) { total, next -> total + next })
                })

        return doubleUpIfOneWinsAllKoos(gameResult)
    }

    private fun getKooResult(
        kooBoardCards: List<PokerCard>,
        getPlayerKooHand: KFunction1<Player, List<PokerCard>>
    ): List<PlayerResult> {

        val kooBestHand = dealer.calcBestHand(
            kooBoardCards.toTypedArray(),
            players.map { player -> PlayerOmahaHand(player.name(), OmahaHand(getPlayerKooHand(player))) })

        return players.map { player ->
            if (kooBestHand.any { bh -> bh.playerId == player.name() })
                PlayerResult(player.name(), 1.0 / kooBestHand.size)
            else
                PlayerResult(player.name(), 0.0)
        }

    }

    private fun doubleUpIfOneWinsAllKoos(gameResult: GameResult): GameResult {
        return if (gameResult.playersResult.any { pr -> pr.resultsPoints == 3.0 })
            GameResult(
                gameResult.playersResult
                    .map { pr -> PlayerResult(pr.playerId, pr.resultsPoints * players.size) }
            )
        else gameResult
    }


}

data class GameResult(val playersResult: List<PlayerResult>)

data class PlayerResult(val playerId: String, val resultsPoints: Double)
