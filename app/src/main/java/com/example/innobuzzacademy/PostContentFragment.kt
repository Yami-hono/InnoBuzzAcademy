package com.example.innobuzzacademy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.innobuzzacademy.databinding.FragmentPostContentBinding

class PostContentFragment : Fragment() {

    var postId =0
    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postId=it.getInt("POST_ID")

        }
    }

    lateinit var binding: FragmentPostContentBinding
    lateinit var title:TextView
    lateinit var body:TextView
    lateinit var id:TextView
    lateinit var userId:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_content, container, false)

        addObserver()

        binding.viewModel=mainViewModel
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.run {
                        supportFragmentManager.beginTransaction().remove(this@PostContentFragment)
                            .commitAllowingStateLoss()
                    }
                }
            }
            )
        val view= inflater.inflate(R.layout.fragment_post_content, container, false)

         title=view.findViewById(R.id.title)
         body=view.findViewById(R.id.body)
         id=view.findViewById(R.id.id)
         userId=view.findViewById(R.id.userId)

        body.setOnClickListener {
        }



        return view
    }

    fun addObserver(){
        mainViewModel.database.contactDAO().getPostData(postId).observe(viewLifecycleOwner){
            title.text="Title:-"+it.title
            body.text="Body:-"+it.body
            id.text="ID:-"+it.id.toString()
            userId.text="UserId:-"+it.userId.toString()

        }

    }
}