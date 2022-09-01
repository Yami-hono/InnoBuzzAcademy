package com.example.innobuzzacademy

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaunch.network.RetrofitInstance
import com.example.innobuzzacademy.database.Post
import com.example.innobuzzacademy.database.PostDatabase
import com.example.innobuzzacademy.network.NetworkService
import kotlinx.coroutines.launch
import kotlin.Exception

class MainViewModel:ViewModel() {

    lateinit var database:PostDatabase
    var title= ObservableField<String>()

    fun getAPIData(){
        viewModelScope.launch {
            try{
                val retrofitInstance= RetrofitInstance.getRetrofitInstance().create(NetworkService::class.java)
                val res=retrofitInstance.getPostList()
                if(res.isSuccessful){
//                    postData.value=res.body()
                    for(i in res.body()!!){
                        insertIntoDB(i)
                    }
                }
                else{
                }
            }catch (ex:Exception){
            }
        }
    }

    fun insertIntoDB(user: Post){
        viewModelScope.launch {
            try {
                database.contactDAO().insert(user)
            }catch (ex:Exception){
            }
        }
    }
}

interface Call {
    fun itemClick(userId: Int)
}