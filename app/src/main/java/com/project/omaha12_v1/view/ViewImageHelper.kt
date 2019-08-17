package com.project.omaha12_v1.view

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.project.omaha12_v1.cards.PokerCard

class ViewImageHelper(private val assets: AssetManager) {

    fun setImageFor(pokerCard: PokerCard, context: Context): ImageView {
        val imageView = ImageView(context)
        imageView.setImageBitmap(getBitmapPath(pokerCard.toString()))
        imageView.tag = pokerCard.toString()
        return imageView
    }

    fun getBitmapPath(bit: String): Bitmap? {
        return BitmapFactory.decodeStream(assets.open(bit.toLowerCase() + ".png"))
    }
}