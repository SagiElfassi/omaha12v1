package com.project.omaha12_v1.game

import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.dealers.Dealer

class Omaha12Game(val dealer: Dealer, var gameBoard: GameBoard) {

    fun startNewGame() {
        println(dealer.getDeck())
        dealer.shuffle()
        println(dealer.getDeck())

        // Draw...

        // Open flops

        // Open turns and rivers

        // Announce winner
    }

    fun open3Flops() {
        gameBoard = GameBoard(dealer.open3Flops())
    }

    fun openTurnAndRiver() {
        gameBoard = GameBoard(dealer.openTurnAndRiver())
    }

    fun calculateResult() {}
}