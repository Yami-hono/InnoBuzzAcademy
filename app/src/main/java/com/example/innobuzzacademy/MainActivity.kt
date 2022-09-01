package com.example.innobuzzacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.innobuzzacademy.adapter.PostListAdapter
import com.example.innobuzzacademy.database.Post
import com.example.innobuzzacademy.database.PostDatabase
import com.example.innobuzzacademy.databinding.ActivityMainBinding
import com.google.android.material.transition.MaterialSharedAxis

class MainActivity : AppCompatActivity(), Call{

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var userListAdapter: PostListAdapter
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel.database= Room.databaseBuilder(applicationContext,
        PostDatabase::class.java,
        "PostTable")
            .fallbackToDestructiveMigration()
            .build()
        userListAdapter= PostListAdapter(this)
        binding.recyclerView.adapter=userListAdapter
        binding.permission.setOnClickListener {
            Toast.makeText(this, "permission", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }
        viewModel.getAPIData()

        addObserver()
    }

    private fun addObserver() {
        viewModel.database.contactDAO().getdata().observe(this){
            userListAdapter.setUpdatedList(it as ArrayList<Post> )

        }
    }

    override fun itemClick(id: Int) {

        val fragment = PostContentFragment()
        val bundle = Bundle()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragment?.apply {
            exitTransition = MaterialSharedAxis(
                MaterialSharedAxis.Z,
                /* forward= */ false
            ).apply {
                duration = 500
            }
        }
        bundle.putInt("POST_ID", id)
        fragment.arguments = bundle
        fragmentTransaction.replace(R.id.main, fragment)
        fragmentTransaction.commit()

    }
}