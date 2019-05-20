package com.project.omaha12_v1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.cards.CardDeck
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.cards.CardDeckImpl
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.dealers.Dealer
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.players.Player

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game = Omaha12Game(
            DealerImpl(
                CardDeckFactory().build()), GameBoard(null)).startNewGame()
    }
}
