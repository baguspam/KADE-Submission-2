package com.maspamz.submission2kotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.DragEvent
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.maspamz.submission2kotlin.model.League
import org.jetbrains.anko.*

/**
 * Created by PU on 9/7/2018.
 */
class DetailActivityClub : AppCompatActivity() {

    private var name: String = ""
    private var desc: String = ""
    private var img: String = ""
    lateinit var nameClubTV: TextView
    lateinit var descClubTV: TextView
    lateinit var imgClubIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Club with Anko"
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            scrollView {
                isVerticalScrollBarEnabled = false
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(10)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        imgClubIV = imageView {}.lparams(height = dip(100))

                        nameClubTV = textView {
                            this.gravity = Gravity.CENTER
                            textSize = 16f
                            textColor = ContextCompat.getColor(context, R.color.colorAccent)
                        }

                        descClubTV = textView().lparams {
                            topMargin = dip(15)
                        }
                    }
                }
            }
        }

        val intent = intent
        name = intent.getStringExtra("NAME_ITEM")
        desc = intent.getStringExtra("DESC_ITEM")
        img = intent.getStringExtra("IMG_ITEM")
        Glide.with(this).load(img).into(imgClubIV)
        nameClubTV.text = name
        descClubTV.text = desc

    }
}