package com.project.omaha12_v1

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import com.project.omaha12_v1.players.PlayerImpl
import kotlinx.android.synthetic.main.game_screen.*

class GameScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        setContentView(R.layout.game_screen)

        val cards = CardDeckFactory().build()
        val players = listOf(
            PlayerImpl("Alonm", listOf(), listOf(), listOf(), listOf()),
            PlayerImpl("Sagia", listOf(), listOf(), listOf(), listOf())
        )

        val game = Omaha12Game(
            DealerImpl(cards, ShowDownEvaluatorImpl()),
            GameBoardImpl(),
            players
        )

        game.startNewGame()

        player1card1.text = players[0].cards()[0].toString()
        player1card2.text = players[0].cards()[1].toString()
        player1card3.text = players[0].cards()[2].toString()
        player1card4.text = players[0].cards()[3].toString()
        player1card5.text = players[0].cards()[4].toString()
        player1card6.text = players[0].cards()[5].toString()
        player1card7.text = players[0].cards()[6].toString()
        player1card8.text = players[0].cards()[7].toString()
        player1card9.text = players[0].cards()[8].toString()
        player1card10.text = players[0].cards()[9].toString()
        player1card11.text = players[0].cards()[10].toString()
        player1card12.text = players[0].cards()[11].toString()

        player2card1.text = players[1].cards()[0].toString()
        player2card2.text = players[1].cards()[1].toString()
        player2card3.text = players[1].cards()[2].toString()
        player2card4.text = players[1].cards()[3].toString()
        player2card5.text = players[1].cards()[4].toString()
        player2card6.text = players[1].cards()[5].toString()
        player2card7.text = players[1].cards()[6].toString()
        player2card8.text = players[1].cards()[7].toString()
        player2card9.text = players[1].cards()[8].toString()
        player2card10.text = players[1].cards()[9].toString()
        player2card11.text = players[1].cards()[10].toString()
        player2card12.text = players[1].cards()[11].toString()

        tempCard.setImageResource(R.drawable.ace_spade)

        game.open3Flops()

        val firstFlop = game.gameBoard.firstKoo()
        val secondFlop = game.gameBoard.secondKoo()
        val thirdFlop = game.gameBoard.thirdKoo()

        flop1card1.text = firstFlop[0].toString()
        flop1card2.text = firstFlop[1].toString()
        flop1card3.text = firstFlop[2].toString()

        flop2card1.text = secondFlop[0].toString()
        flop2card2.text = secondFlop[1].toString()
        flop2card3.text = secondFlop[2].toString()

        flop3card1.text = thirdFlop[0].toString()
        flop3card2.text = thirdFlop[1].toString()
        flop3card3.text = thirdFlop[2].toString()

    }
}
