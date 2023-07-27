package com.example.androidtestdemo.room_db_tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidtestdemo.model.Quotes
import com.example.androidtestdemo.room_db.QuotesDao
import com.example.androidtestdemo.room_db.QuotesDb
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.sin


class QuotesDaoTests {

    lateinit var quotesDao: QuotesDao
    lateinit var quotesDb: QuotesDb

    @get:Rule
    val instantExcautorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        quotesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuotesDb::class.java
        ).allowMainThreadQueries().build()
        quotesDao = quotesDb.getQuotesDao()
    }

    @Test
    fun insertSingleQuoteAndTestIfItExistsInDb()= runBlocking{
        val singleQuote = Quotes(0,"This is a test quote","Samriddha Samanta")
        quotesDao.insertQuote(singleQuote)
        val result = quotesDao.getQuotes().getOrAwaitValue().find { quote -> quote.author==singleQuote.author }
        assertEquals(singleQuote.author,result?.author)
    }

    @Test
    fun testDeleteAllQuotesFunction()= runBlocking{
        val singleQuote1 = Quotes(0,"This is a test quote 0","Samriddha Samanta")
        val singleQuote2 = Quotes(0,"This is a test quote 1","Shilpa Samanta")
        val singleQuote3 = Quotes(0,"This is a test quote 2","Mira Samanta")
        quotesDao.insertQuote(singleQuote1)
        quotesDao.insertQuote(singleQuote2)
        quotesDao.insertQuote(singleQuote3)
        quotesDao.deleteAll()
        assertEquals(0,quotesDao.getQuotes().getOrAwaitValue().size)
    }

    @Test
    fun testDeleteSingleQuoteFromQuotesList()= runBlocking{
        val singleQuote1 = Quotes(0,"This is a test quote 0","Samriddha Samanta")
        val singleQuote2 = Quotes(0,"This is a test quote 1","Shilpa Samanta")
        val singleQuote3 = Quotes(0,"This is a test quote 2","Mira Samanta")
        quotesDao.insertQuote(singleQuote1)
        quotesDao.insertQuote(singleQuote2)
        quotesDao.insertQuote(singleQuote3)
        quotesDao.deleteSingleQuote(singleQuote2)
        val result  = quotesDao.getQuotes().getOrAwaitValue()
        val isPresent = result.contains(singleQuote2)
        assertEquals(false,isPresent)
    }

    @After
    fun removeSetUp(){
        quotesDb.close()
    }
}