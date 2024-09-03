package com.example.quotatious

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.enter)


        btn.setOnClickListener{
            btn.animate().apply {
                duration = 100
                scaleXBy(0.2f)
                scaleYBy(0.2f)
            }.withEndAction{
                btn.animate().apply {
                    duration = 100
                    scaleXBy(-0.2f)
                    scaleYBy(-0.2f)
                }
            }.start()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}