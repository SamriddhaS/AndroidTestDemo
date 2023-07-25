package com.example.androidtestdemo.utils

import android.content.Context
import com.example.androidtestdemo.model.Quotes
import com.google.gson.Gson

class QuotesManager {
    var quoteList = emptyArray<Quotes> ()
    var currentQuoteIndex = 0

    fun populateQuoteFromAssets (context: Context, fileName: String){
        val inputStream = context.assets.open (fileName)
        val size: Int = inputStream.available ()
        val buffer = ByteArray (size)
        inputStream.read(buffer)
        inputStream.close ()
        val json = String (buffer, Charsets.UTF_8)
        val gson = Gson ()
        quoteList = gson. fromJson (json, Array<Quotes>::class.java)
    }

    fun populateQuotes (quotes: Array<Quotes>){
        quoteList = quotes
    }

    fun getCurrentQuote (): Quotes {
        return quoteList[currentQuoteIndex]
    }

    fun getNextQuote () : Quotes {
        if (currentQuoteIndex == quoteList.size - 1) return quoteList [currentQuoteIndex]
        return quoteList [++currentQuoteIndex]
    }
    fun getPreviousQuote () : Quotes {
        if (currentQuoteIndex == 0) return quoteList[currentQuoteIndex]
        return quoteList [--currentQuoteIndex]
    }
}