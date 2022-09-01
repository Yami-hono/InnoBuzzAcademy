package com.example.innobuzzacademy.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostTable")
data class Post(
    val userId:Int,
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val title:String,
    val body:String

)
