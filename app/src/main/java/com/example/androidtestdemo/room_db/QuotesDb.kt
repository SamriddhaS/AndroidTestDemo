package com.example.androidtestdemo.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtestdemo.model.Quotes

@Database(entities = [Quotes::class], version = 1)
abstract class QuotesDb:RoomDatabase() {
    abstract fun getQuotesDao():QuotesDao
}