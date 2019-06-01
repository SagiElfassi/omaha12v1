package com.project.omaha12_v1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.project.omaha12_v1.board.GameBoard
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.game.Omaha12Game
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cards = CardDeckFactory().build()

        button1.setOnClickListener {
            val game = Omaha12Game(
                DealerImpl(
                    cards,
                    ShowDownEvaluatorImpl()
                ), GameBoard(null)
            ).startNewGame()
            Toast.makeText(this, "${cards.takeCard().value}", Toast.LENGTH_LONG).show()
        }
    }


}
