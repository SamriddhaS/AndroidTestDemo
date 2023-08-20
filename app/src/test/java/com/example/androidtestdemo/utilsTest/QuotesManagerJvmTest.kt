package com.example.androidtestdemo.utilsTest

import android.content.Context
import android.content.res.AssetManager
import com.example.androidtestdemo.model.Quotes
import com.example.androidtestdemo.utils.QuotesManager
import com.google.gson.JsonSyntaxException
import com.nhaarman.mockitokotlin2.doReturn

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.FileNotFoundException

class QuotesManagerJvmTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetManager:AssetManager

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // We are mocking the input stream by using mocked context and assest manager class.
        val mockTestStream = QuotesManagerJvmTest::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetManager).`when`(context).assets // Link our mocked assetManager class whenever context.assets is called.
        Mockito.`when`(context.assets.open(anyString())).thenReturn(mockTestStream) // return our mocked test stream when context.assets.open() is called
    }

    @After
    fun tearDown() {
    }

    @Test(expected = NullPointerException::class)
    fun populateQuoteFromAssets() {
        val mockTestStream = QuotesManagerJvmTest::class.java.getResourceAsStream("/dasdasd.json")
        doReturn(assetManager).`when`(context).assets // Link our mocked assetManager class whenever context.assets is called.
        Mockito.`when`(context.assets.open(anyString())).thenReturn(mockTestStream)
        val quotesManager = QuotesManager()
        quotesManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_invalidJson_expected_exception() {
        val mockTestStream = QuotesManagerJvmTest::class.java.getResourceAsStream("/quotes_invalid.json")
        doReturn(assetManager).`when`(context).assets // Link our mocked assetManager class whenever context.assets is called.
        Mockito.`when`(context.assets.open(anyString())).thenReturn(mockTestStream)
        val quotesManager = QuotesManager()
        quotesManager.populateQuoteFromAssets(context, "")
    }

    @Test
    fun populateQuoteFromAssets_validJson_expected_count_102() {
        val quotesManager = QuotesManager()
        quotesManager.populateQuoteFromAssets(context, "")
        assertEquals(102, quotesManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        //Arrange
        val quoteManager = QuotesManager()
        quoteManager.populateQuotes(
            arrayOf(
                Quotes(id = 0, quote = "This is first quote", author = "1"),
                Quotes(id = 1, quote = "This is second quote", author = "2"),
                Quotes(id = 2, quote = "This is third quote", author = "3")
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
                Quotes(id = 0, quote = "This is first quote", author = "1"),
                Quotes(id = 1, quote = "This is second quote", author = "2"),
                Quotes(id = 2, quote = "This is third quote", author = "3")
            )
        )
        //Act
        val quote = quoteManager.getNextQuote()
        //assert
        assertEquals("2", quote.author)
    }
}