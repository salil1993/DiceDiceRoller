package com.example.diceroller

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.util.DiceHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dice: IntArray
    private lateinit var headlineText:String
     private val imageViews by lazy {
         arrayOf<ImageView>(
             findViewById(R.id.die1),
             findViewById(R.id.die2),
             findViewById(R.id.die3),
             findViewById(R.id.die4),
             findViewById(R.id.die5)
         )
     }

    private val headline by lazy {
        findViewById<TextView>(R.id.headline)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //    setSupportActionBar(toolbar)

        headlineText = savedInstanceState?.getString(HEADLINE_TEXT)
            ?:getString(R.string.welcome)
        dice = savedInstanceState?.getIntArray(DICE_COLLECTION)
            ?: intArrayOf(6,6,6,6,6)
        fab.setOnClickListener {
            fabclickHandlier()
        }

        lifecycle.addObserver(MyLifeCycleObserver())
     //   headline = findViewById(R.id.headline)
        updateDisplay()

    }

    private fun fabclickHandlier() {
     //   Toast.makeText(this, "Replace with your own action", Toast.LENGTH_LONG).show()

        dice = DiceHelper.rollDice()
        headlineText = DiceHelper.evaluateDice(this,dice)
            updateDisplay()


    }

    private fun updateDisplay() {
        for (i in 0 until imageViews.size) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.die_1
                2 -> R.drawable.die_2
                3 -> R.drawable.die_3
                4 -> R.drawable.die_4
                5 -> R.drawable.die_5
                6 -> R.drawable.die_6
                else -> R.drawable.die_6

            }
            imageViews[i].setImageResource(drawableId)
        }
        headline.text = headlineText
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.putString(HEADLINE_TEXT,headlineText)
        outState?.putIntArray(DICE_COLLECTION,dice)
        super.onSaveInstanceState(outState)
    }

}
