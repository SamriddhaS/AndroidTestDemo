package com.example.androidtestdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val quote:String,
    val author:String
)
