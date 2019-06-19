package com.project.omaha12_v1

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import com.project.omaha12_v1.players.PlayerImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        setContentView(R.layout.activity_main)


        val cards = CardDeckFactory().build()
        val players = listOf(
            PlayerImpl("Alonm", listOf(), listOf(), listOf(), listOf()),
            PlayerImpl("Sagia", listOf(), listOf(), listOf(), listOf())
        )

        StartNewGame.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
        val game = Omaha12Game(
            DealerImpl(cards, ShowDownEvaluatorImpl()),
            GameBoardImpl(),
            players
        ).startNewGame()
    }
}
