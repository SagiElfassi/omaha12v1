package com.project.omaha12_v1

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import com.project.omaha12_v1.players.PlayerImpl
import kotlinx.android.synthetic.main.game_screen.*

class GameScreen : AppCompatActivity() {

    private lateinit var game: Omaha12Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        setContentView(R.layout.game_screen)

        val cards = CardDeckFactory().build()
        val players = listOf(
            PlayerImpl("Alonm", listOf(), listOf(), listOf(), listOf()),
            PlayerImpl("Sagia", listOf(), listOf(), listOf(), listOf())
        )

        game = Omaha12Game(
            DealerImpl(cards, ShowDownEvaluatorImpl()),
            GameBoardImpl(),
            players
        )

        game.startNewGame()

        initialCardsImg(players)

        open3Flops(game)

        iamReadyButton.setOnClickListener {
            game.openTurnAndRivers()

            val firstFlop = game.gameBoard.firstKoo()
            val secondFlop = game.gameBoard.secondKoo()
            val thirdFlop = game.gameBoard.thirdKoo()


            Handler().postDelayed({
                turn1.setImageBitmap(getBitmapPath(firstFlop[3].toString()))
                river1.setImageBitmap(getBitmapPath(firstFlop[4].toString()))
            }, 2000)

            Handler().postDelayed({
                turn2.setImageBitmap(getBitmapPath(secondFlop[3].toString()))
                river2.setImageBitmap(getBitmapPath(secondFlop[4].toString()))
            }, 4000)

            Handler().postDelayed({
                turn3.setImageBitmap(getBitmapPath(thirdFlop[3].toString()))
                river3.setImageBitmap(getBitmapPath(thirdFlop[4].toString()))
            }, 6000)

        }
    }

    private fun open3Flops(game: Omaha12Game) {
        game.open3Flops()

        val firstFlop = game.gameBoard.firstKoo()
        val secondFlop = game.gameBoard.secondKoo()
        val thirdFlop = game.gameBoard.thirdKoo()

        flop1card1.setImageBitmap(getBitmapPath(firstFlop[0].toString()))
        flop1card2.setImageBitmap(getBitmapPath(firstFlop[1].toString()))
        flop1card3.setImageBitmap(getBitmapPath(firstFlop[2].toString()))

        flop2card1.setImageBitmap(getBitmapPath(secondFlop[0].toString()))
        flop2card2.setImageBitmap(getBitmapPath(secondFlop[1].toString()))
        flop2card3.setImageBitmap(getBitmapPath(secondFlop[2].toString()))

        flop3card1.setImageBitmap(getBitmapPath(thirdFlop[0].toString()))
        flop3card2.setImageBitmap(getBitmapPath(thirdFlop[1].toString()))
        flop3card3.setImageBitmap(getBitmapPath(thirdFlop[2].toString()))
    }

    private fun initialCardsImg(players: List<PlayerImpl>) {
        player1card1.setImageBitmap(getBitmapPath(players[0].cards()[0].toString()))
        player1card1.setOnTouchListener(cardMoveListener)
        player1card2.setImageBitmap(getBitmapPath(players[0].cards()[1].toString()))
        player1card2.setOnTouchListener(cardMoveListener)
        player1card3.setImageBitmap(getBitmapPath(players[0].cards()[2].toString()))
        player1card3.setOnTouchListener(cardMoveListener)
        player1card4.setImageBitmap(getBitmapPath(players[0].cards()[3].toString()))
        player1card4.setOnTouchListener(cardMoveListener)
        player1card5.setImageBitmap(getBitmapPath(players[0].cards()[4].toString()))
        player1card5.setOnTouchListener(cardMoveListener)
        player1card6.setImageBitmap(getBitmapPath(players[0].cards()[5].toString()))
        player1card6.setOnTouchListener(cardMoveListener)
        player1card7.setImageBitmap(getBitmapPath(players[0].cards()[6].toString()))
        player1card7.setOnTouchListener(cardMoveListener)
        player1card8.setImageBitmap(getBitmapPath(players[0].cards()[7].toString()))
        player1card8.setOnTouchListener(cardMoveListener)
        player1card9.setImageBitmap(getBitmapPath(players[0].cards()[8].toString()))
        player1card9.setOnTouchListener(cardMoveListener)
        player1card10.setImageBitmap(getBitmapPath(players[0].cards()[9].toString()))
        player1card10.setOnTouchListener(cardMoveListener)
        player1card11.setImageBitmap(getBitmapPath(players[0].cards()[10].toString()))
        player1card11.setOnTouchListener(cardMoveListener)
        player1card12.setImageBitmap(getBitmapPath(players[0].cards()[11].toString()))
        player1card12.setOnTouchListener(cardMoveListener)

        player2card1.setImageBitmap(getBitmapPath(players[1].cards()[0].toString()))
        player2card2.setImageBitmap(getBitmapPath(players[1].cards()[1].toString()))
        player2card3.setImageBitmap(getBitmapPath(players[1].cards()[2].toString()))
        player2card4.setImageBitmap(getBitmapPath(players[1].cards()[3].toString()))
        player2card5.setImageBitmap(getBitmapPath(players[1].cards()[4].toString()))
        player2card6.setImageBitmap(getBitmapPath(players[1].cards()[5].toString()))
        player2card7.setImageBitmap(getBitmapPath(players[1].cards()[6].toString()))
        player2card8.setImageBitmap(getBitmapPath(players[1].cards()[7].toString()))
        player2card9.setImageBitmap(getBitmapPath(players[1].cards()[8].toString()))
        player2card10.setImageBitmap(getBitmapPath(players[1].cards()[9].toString()))
        player2card11.setImageBitmap(getBitmapPath(players[1].cards()[10].toString()))
        player2card12.setImageBitmap(getBitmapPath(players[1].cards()[11].toString()))
    }

    private fun getBitmapPath(bit: String): Bitmap? {
        return BitmapFactory.decodeStream(assets.open(bit.toLowerCase() + ".png"))
    }


    private val cardMoveListener = View.OnTouchListener(function = { view, motionEvent ->

        if (motionEvent.action == MotionEvent.ACTION_MOVE) {

            view.y = motionEvent.rawY - view.height / 2
            view.x = motionEvent.rawX - view.width / 2
        }
        true
    })
}
