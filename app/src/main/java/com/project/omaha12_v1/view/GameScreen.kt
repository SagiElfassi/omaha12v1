package com.project.omaha12_v1.view

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.project.omaha12_v1.R
import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import com.project.omaha12_v1.players.PlayerImpl
import com.project.omaha12_v1.view.listener.CardsDragAndDropListener
import com.project.omaha12_v1.view.listener.PlayerCardDragNDropListener
import kotlinx.android.synthetic.main.game_screen.*


class GameScreen : AppCompatActivity() {

    private lateinit var game: Omaha12Game

    private lateinit var imageHelper :ViewImageHelper

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageHelper = ViewImageHelper(assets)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)

        setContentView(R.layout.game_screen)

        initiateIamReadyButton()

        val cards = CardDeckFactory().build()
        val players = listOf(
            PlayerImpl("Alonm", listOf(), OmahaHand(listOf()), OmahaHand(listOf()), OmahaHand(listOf())),
            PlayerImpl("Sagia", listOf(), OmahaHand(listOf()), OmahaHand(listOf()), OmahaHand(listOf()))
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

    private fun initiateIamReadyButton() {
        iamReadyButton.setOnClickListener {
            game.openTurnAndRivers()

            val firstFlop = game.gameBoard.firstKoo()
            val secondFlop = game.gameBoard.secondKoo()
            val thirdFlop = game.gameBoard.thirdKoo()

            Handler().postDelayed({
                turn1.addView(imageHelper.setImageFor(firstFlop[3], this))
                river1.addView(imageHelper.setImageFor(firstFlop[4], this))
            }, 2000)

            Handler().postDelayed({
                turn2.addView(imageHelper.setImageFor(secondFlop[3], this))
                river2.addView(imageHelper.setImageFor(secondFlop[4], this))
            }, 4000)

            Handler().postDelayed({
                turn3.addView(imageHelper.setImageFor(thirdFlop[3], this))
                river3.addView(imageHelper.setImageFor(thirdFlop[4], this))
            }, 6000)

            // For calculations the hands
            val omHandString1 = flop1slot1.getChildAt(0).tag.toString() + flop1slot2.getChildAt(0).tag.toString() +
                    flop1slot3.getChildAt(0).tag.toString() + flop1slot4.getChildAt(0).tag.toString()
            val omHandString2 = flop2slot1.getChildAt(0).tag.toString() + flop2slot2.getChildAt(0).tag.toString() +
                    flop2slot3.getChildAt(0).tag.toString() + flop2slot4.getChildAt(0).tag.toString()
            val omHandString3 = flop3slot1.getChildAt(0).tag.toString() + flop3slot2.getChildAt(0).tag.toString() +
                    flop3slot3.getChildAt(0).tag.toString() + flop3slot4.getChildAt(0).tag.toString()

            game.players[0].setHandToFirstFlop(OmahaHand.fromString(omHandString1)!!)
            game.players[0].setHandToSecondFlop(OmahaHand.fromString(omHandString2)!!)
            game.players[0].setHandToThirdFlop(OmahaHand.fromString(omHandString3)!!)

            // Just for tests
            game.players[1].setHandToFirstFlop(OmahaHand.fromString("AsAcKsKc")!!)
            game.players[1].setHandToSecondFlop(OmahaHand.fromString("AdAcKdKc")!!)
            game.players[1].setHandToThirdFlop(OmahaHand.fromString("QsQdJsJd")!!)

            Log.d("TAG", game.calculateResult().playersResult[0].kooPoints.toString())
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

        firstFlopCards.forEachIndexed { index, linearLayout ->
            linearLayout.addView(imageHelper.setImageFor(firstFlop[index], this))
        }
        secondFlopCards.forEachIndexed { index, linearLayout ->
            linearLayout.addView(imageHelper.setImageFor(secondFlop[index], this))
        }
        thirdFlopCards.forEachIndexed { index, linearLayout ->
            linearLayout.addView(imageHelper.setImageFor(thirdFlop[index], this))
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

        val slotNormal = resources.getDrawable(R.drawable.shape)
        val slotEntered = resources.getDrawable(R.drawable.enter_shape)

        player1CardViews.forEachIndexed { index, linearLayout ->
            val imageView = imageHelper.setImageFor(players[0].cards()[index], this)
            setOnTouchListenerFor(imageView)
            linearLayout.addView(imageView)
            linearLayout.setOnDragListener(
                PlayerCardDragNDropListener(
                    slotNormal,
                    slotEntered
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
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

        slots.forEach { it.background = slotNormal }

        slots.forEach {
            it.setOnDragListener(
                CardsDragAndDropListener(
                    slotNormal,
                    slotEntered
                )
            )
        }
    }

    @SuppressLint("NewApi", "ClickableViewAccessibility")
    private fun setOnTouchListenerFor(cardImageView: ImageView?) {
        cardImageView!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(v)
                v.startDragAndDrop(data, shadowBuilder, v, 0)

                true
            } else {
                false
            }
        }
    }

}
