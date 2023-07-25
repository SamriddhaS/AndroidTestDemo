package com.example.androidtestdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        if (intent.hasExtra("TEXT_STRING")){
            val text = intent.getStringExtra("TEXT_STRING")
            findViewById<TextView>(R.id.text1).text = text
            Log.d("TAG",text?:"No text")
        }


    }
}