package com.learning.porter_api_and_db.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.porter_api_and_db.databinding.ItemPostsBinding

class PostAdapter(val itemList: List<DataModel>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(binding: ItemPostsBinding):RecyclerView.ViewHolder(binding.root) {
        val id = binding.tvId
        val title = binding.tvTitle
        val body = binding.tvBody

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = ItemPostsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = itemList[position]
        holder.id.text = post.id.toString()
        holder.title.text = post.title
        holder.body.text = post.body
    }
}