package com.project.omaha12_v1

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import com.project.omaha12_v1.players.PlayerImpl
import kotlinx.android.synthetic.main.game_screen.*


class GameScreen : AppCompatActivity() {

    private lateinit var game: Omaha12Game

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        setContentView(R.layout.game_screen)

        initiatIamReadyButton()

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
        initSlots()

        open3Flops(game)
    }

    private fun initiatIamReadyButton() {
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

        val firstFlopCards = listOf(flop1card1, flop1card2, flop1card3)
        val secondFlopCards = listOf(flop2card1, flop2card2, flop2card3)
        val thirdFlopCards = listOf(flop3card1, flop3card2, flop3card3)

        firstFlopCards.forEachIndexed { index, imageView ->
            setImageFor(firstFlop[index], imageView)
        }
        secondFlopCards.forEachIndexed { index, imageView ->
            setImageFor(secondFlop[index], imageView)
        }
        thirdFlopCards.forEachIndexed { index, imageView ->
            setImageFor(thirdFlop[index], imageView)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initialCardsImg(players: List<PlayerImpl>) {
        val player1CardViews = listOf(
            player1card1,
            player1card2,
            player1card3,
            player1card4,
            player1card5,
            player1card6,
            player1card7,
            player1card8,
            player1card9,
            player1card10,
            player1card11,
            player1card12
        )
        val player2CardViews = listOf(
            player2card1,
            player2card2,
            player2card3,
            player2card4,
            player2card5,
            player2card6,
            player2card7,
            player2card8,
            player2card9,
            player2card10,
            player2card11,
            player2card12
        )

        player1CardViews.forEachIndexed { index, imageView ->
            setOnTouchListenerFor(imageView)
            setImageFor(players[0].cards()[index], imageView)
        }

        player2CardViews.forEachIndexed { index, imageView ->
            setImageFor(players[1].cards()[index], imageView)
        }

    }

    private fun initSlots() {
        val slotNormal = resources.getDrawable(R.drawable.shape)
        val slotEntered = resources.getDrawable(R.drawable.enter_shape)

        val slots = listOf(
            flop1slot1,
            flop1slot2,
            flop1slot3,
            flop1slot4,
            flop2slot1,
            flop2slot2,
            flop2slot3,
            flop2slot4,
            flop3slot1,
            flop3slot2,
            flop3slot3,
            flop3slot4
        )

        slots.forEach { it.setOnDragListener(CardsDragAndDropListener(slotNormal, slotEntered)) }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListenerFor(cardImageView: ImageView?) {
        cardImageView!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(v)
                v.startDrag(data, shadowBuilder, v, 0)
                true
            } else {
                false
            }
        }
    }

    private fun setImageFor(pokerCard: PokerCard, cardImageView: ImageView?) {
        cardImageView!!.setImageBitmap(getBitmapPath(pokerCard.toString()))

    }


    private fun getBitmapPath(bit: String): Bitmap? {
        return BitmapFactory.decodeStream(assets.open(bit.toLowerCase() + ".png"))
    }
}
