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
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.PokerCard.Companion.fromString
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.ShowDownEvaluator
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
                val imageView1 = ImageView(this)
                val imageView2 = ImageView(this)
                imageView1.setImageBitmap(getBitmapPath(firstFlop[3].toString()))
                turn1.addView(imageView1)
                imageView2.setImageBitmap(getBitmapPath(firstFlop[4].toString()))
                river1.addView(imageView2)
            }, 2000)

            Handler().postDelayed({
                val imageView1 = ImageView(this)
                val imageView2 = ImageView(this)
                imageView1.setImageBitmap(getBitmapPath(secondFlop[3].toString()))
                turn2.addView(imageView1)
                imageView2.setImageBitmap(getBitmapPath(secondFlop[4].toString()))
                river2.addView(imageView2)
            }, 4000)

            Handler().postDelayed({
                val imageView1 = ImageView(this)
                val imageView2 = ImageView(this)
                imageView1.setImageBitmap(getBitmapPath(thirdFlop[3].toString()))
                turn3.addView(imageView1)
                imageView2.setImageBitmap(getBitmapPath(thirdFlop[4].toString()))
                river3.addView(imageView2)
            }, 6000)

            val omHandString = flop1slot1.getChildAt(0).tag.toString() + flop1slot2.getChildAt(0).tag.toString()+
                    flop1slot3.getChildAt(0).tag.toString() + flop1slot4.getChildAt(0).tag.toString()

            val firstKooOmahaHand = OmahaHand.fromString(omHandString)!!

            game.players[0].setHandToFirstFlop(firstKooOmahaHand)

            Log.d("TAG", firstKooOmahaHand.cards[2].toString())
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
            setImageFor(firstFlop[index], linearLayout)
        }
        secondFlopCards.forEachIndexed { index, linearLayout ->
            setImageFor(secondFlop[index], linearLayout)
        }
        thirdFlopCards.forEachIndexed { index, linearLayout ->
            setImageFor(thirdFlop[index], linearLayout)
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
/*        val player2CardViews = listOf(
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
        )*/

            player1CardViews.forEachIndexed { index, linearLayout ->
            setImageFor(players[0].cards()[index], linearLayout)
        }

        /*player2CardViews.forEachIndexed { index, imageView ->
            setImageFor(players[1].cards()[index], imageView)
        }*/

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

        slots.forEach { it.setOnDragListener(CardsDragAndDropListener(slotNormal, slotEntered)) }
    }

    @SuppressLint( "NewApi", "ClickableViewAccessibility")
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

    private fun setImageFor(pokerCard: PokerCard, cardImageView: LinearLayout?) {
        val imageView = ImageView(this)
        imageView!!.setImageBitmap(getBitmapPath(pokerCard.toString()))
        imageView.tag = pokerCard.toString()
        setOnTouchListenerFor(imageView)
        cardImageView!!.addView(imageView)
    }

    private fun getBitmapPath(bit: String): Bitmap? {
        return BitmapFactory.decodeStream(assets.open(bit.toLowerCase() + ".png"))
    }
}
