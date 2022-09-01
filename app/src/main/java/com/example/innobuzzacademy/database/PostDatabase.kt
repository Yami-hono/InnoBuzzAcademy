package com.example.innobuzzacademy.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 2)
abstract class PostDatabase :RoomDatabase(){

    abstract fun contactDAO():PostDao
}