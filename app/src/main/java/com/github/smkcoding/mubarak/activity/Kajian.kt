package com.github.smkcoding.mubarak.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.smkcoding.mubarak.R
import kotlinx.android.synthetic.main.components_basic_actionbar.*

class Kajian : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kajian)

        viewProps()
    }

    private fun viewProps() {
        // Set action bar title
        action_bar_title.setText("Kajian Masjid")
    }



}