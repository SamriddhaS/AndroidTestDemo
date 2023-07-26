package com.example.androidtestdemo.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidtestdemo.model.Quotes

@Dao
interface QuotesDao {
    @Insert
    suspend fun insertQuote (quote: Quotes)

    @Update
    suspend fun updateQuote (quote: Quotes)

    @Query ("DELETE FROM quotes")
    suspend fun delete()

    @Query ("SELECT * FROM quotes")
    fun getQuotes ():LiveData<List<Quotes>>

    @Query("SELECT * FROM quotes where id=:quoteId")
    suspend fun getQuoteById(quoteId: Int): Quotes
}