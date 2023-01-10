package com.example.androidtestdemo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.androidtestdemo.model.Quotes
import com.google.gson.JsonSyntaxException
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class QuotesManagerTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    /*
    * When we add the "expected=FileNotFoundException::class" that means
    * we are expecting an exception and if an exception is thrown then the
    * test case should pass.
    * */
    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_invalidJson_expected_exception() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "quotes_invalid.json")
    }

    @Test
    fun populateQuoteFromAssets_validJson_expected_count_102() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "quotes.json")
        assertEquals(102, quotesManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        //Arrange
        val quoteManager = QuotesManager()
        quoteManager.populateQuotes(
            arrayOf(
                Quotes(quote = "This is first quote", author = "1"),
                Quotes(quote = "This is second quote", author = "2"),
                Quotes(quote = "This is third quote", author = "3")
            )
        )
        //Act
        val quote = quoteManager.getPreviousQuote()
        //assert
        assertEquals("1", quote.author)
    }

    @Test
    fun testNextQuote_expected_CorrectQuote() {
        //Arrange
        val quoteManager = QuotesManager()
        quoteManager.populateQuotes(
            arrayOf(
                Quotes(quote = "This is first quote", author = "1"),
                Quotes(quote = "This is second quote", author = "2"),
                Quotes(quote = "This is third quote", author = "3")
            )
        )
        //Act
        val quote = quoteManager.getNextQuote()
        //assert
        assertEquals("2", quote.author)
    }
}