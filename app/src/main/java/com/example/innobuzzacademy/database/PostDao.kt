package com.example.innobuzzacademy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

    @Insert
    suspend fun insert(user: Post)


    @Delete
    suspend fun delete(user: Post)

    @Query("SELECT * FROM PostTable")
     fun getdata():LiveData<List<Post>>

     @Query("Select * from PostTable where id=:id")
     fun getPostData(id:Int):LiveData<Post>
}