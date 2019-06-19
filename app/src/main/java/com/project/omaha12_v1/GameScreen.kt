package com.project.omaha12_v1

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.game_screen.*

class GameScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation =  (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        setContentView(R.layout.game_screen)

        Card1.text = "AceSpade"
    }
}
