package com.project.omaha12_v1.game

import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.dealers.Dealer
import com.project.omaha12_v1.players.Player

class Omaha12Game(val dealer: Dealer, var gameBoard: GameBoard, val cardDeck: CardDeck, val players: Sequence<Player>) {

    fun startNewGame() {
        dealer.shuffle(cardDeck)
        dealer.deal(players)
    }

    fun open3Flops() {
        gameBoard = GameBoard(dealer.open3Flops())
    }

    fun openTurnAndRiver() {
        gameBoard = GameBoard(dealer.openTurnAndRiver())
    }

    fun calculateResult() {}
}