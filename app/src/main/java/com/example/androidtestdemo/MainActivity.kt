package com.example.androidtestdemo

import MainViewModel
import MainViewModelFactory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.androidtestdemo.model.Quotes

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

        findViewById<Button>(R.id.btn_hit).setOnClickListener {
            val text = findViewById<EditText>(R.id.edt_enter_input);
            if (text.text.isNullOrEmpty()){
                Toast.makeText(this@MainActivity,"Enter something",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            startActivity(Intent(
                this@MainActivity,
                NewActivity::class.java
            ).putExtra("TEXT_STRING",text.text.toString()))
        }
    }

    fun setQuote(quote:Quotes){
        quoteText.text = quote.quote
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().quote)
        startActivity(intent)
    }
}