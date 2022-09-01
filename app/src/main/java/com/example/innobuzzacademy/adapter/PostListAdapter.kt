package com.example.innobuzzacademy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.innobuzzacademy.Call
import com.example.innobuzzacademy.R
import com.example.innobuzzacademy.database.Post
import com.example.innobuzzacademy.databinding.LiSearchItemBinding


class PostListAdapter(var call: Call): RecyclerView.Adapter<PostListAdapter.UserViewHolder>(){

    var data=ArrayList<Post>()

    fun setUpdatedList(data:ArrayList<Post>){
        this.data= data
        notifyDataSetChanged()
    }


    inner class UserViewHolder(var binding: LiSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            with(binding) {
                binding.postTitle.text=item.title
                userCard.setOnClickListener {
                    item?.let {
                        call.itemClick(it.id)
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = DataBindingUtil.inflate<LiSearchItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.li_search_item,
            parent,
            false
        )
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val searchResult = data.get(position)
        searchResult.let {
                searchResult->
            holder.bind(searchResult)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
