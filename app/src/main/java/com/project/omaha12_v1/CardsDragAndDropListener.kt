package com.project.omaha12_v1

import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner


class CardsDragAndDropListener(
    var slotNormal: Drawable,
    var slotEntered: Drawable
) : View.OnDragListener {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        v!!.visibility = View.INVISIBLE
        v.background = slotEntered

        when (event!!.action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                Log.d("TAG", "onDrag: drag started.")
                return true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v.visibility = View.VISIBLE
                return true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                return true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //v.visibility = View.INVISIBLE
                return true
            }

            DragEvent.ACTION_DROP -> {
                var view = event.localState as ImageView
                var owner = view.parent as ViewGroup
                owner.removeView(view)
                var container = v as LinearLayout
                container.addView(view)
                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                Log.d("TAG", "onDrag: ended.")
                return true
            }

            // An unknown action type was received.
            else -> Log.e("TAG", "Unknown action type received by OnStartDragListener.")
        }

        return true
    }
}
