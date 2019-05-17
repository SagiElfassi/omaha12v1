package com.project.omaha12_v1.game

import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.Dealer
import com.project.omaha12_v1.players.Player

class Omaha12Game(val dealer: Dealer, var gameBoard: GameBoard) {

    fun startNewGame() {
        val cardDeck = CardDeckFactory().build()
        println(cardDeck.allCards)
        dealer.shuffle(cardDeck)
        println(cardDeck.allCards)
        //   dealer.deal(null)
    }

    fun open3Flops() {
        gameBoard = GameBoard(dealer.open3Flops())
    }

    fun openTurnAndRiver() {
        gameBoard = GameBoard(dealer.openTurnAndRiver())
    }

    fun calculateResult() {}
}