package com.project.omaha12_v1.game

import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.dealers.Dealer
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.players.Player

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
        val firstKooBestHand = dealer.calcBestHand(
            gameBoard.firstKoo().toTypedArray(),
            players.map { player -> OmahaHand(player.getFirstFlopCards()) }
        )

        val secondKooBestHand = dealer.calcBestHand(
            gameBoard.firstKoo().toTypedArray(),
            players.map { player -> OmahaHand(player.getSecondFlopCards()) }
        )

        val thirdKooBestHand = dealer.calcBestHand(
            gameBoard.firstKoo().toTypedArray(),
            players.map { player -> OmahaHand(player.getThirdFlopCards()) }
        )

        val firstKooResults = players.filter { player -> OmahaHand(player.getFirstFlopCards()) == firstKooBestHand.second }
            .map { PlayerResult(it.name(), 1) }
        val secondKooResult = players.filter { player -> OmahaHand(player.getSecondFlopCards()) == secondKooBestHand.second }
            .map { PlayerResult(it.name(), 1) }
        val thirdKooResult = players.filter { player -> OmahaHand(player.getThirdFlopCards()) == thirdKooBestHand.second }
            .map { PlayerResult(it.name(), 1) }

        //GameResult(listOf(firstKooResults, secondKooResult, thirdKooResult).map{it.map { it.resultsPoints }}.foldRight(listOf(),)
        return GameResult(listOf())
    }
}

data class GameResult(val playersResult: List<PlayerResult>)

data class PlayerResult(val playerId: String, val resultsPoints: Int)
