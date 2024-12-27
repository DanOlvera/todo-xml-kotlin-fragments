package com.example.kotlinxmltodo.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinxmltodo.databinding.PostItemBinding
import com.example.kotlinxmltodo.model.data.Post

class PostAdapter(
    private var postsList: List<Post>,
    private val listener: PostClickListener
    ):
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val bindingView: PostItemBinding):
        RecyclerView.ViewHolder(bindingView.root), View.OnClickListener {
            private lateinit var post: Post

        init {
            itemView.setOnClickListener(this)
        }
        @SuppressLint("SetTextI18n")
        fun bind(post: Post) {
            this.post = post
            bindingView.postUuid.text = post.uuid.toString()
            bindingView.postTitle.text = post.title
        }

        override fun onClick(p0: View?) {
            listener.onPostClick(post)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val size = postsList.size
        return size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postsList[position])
    }

    interface PostClickListener {
        fun onPostClick(post: Post)
    }

    internal fun updatePosts(posts: List<Post>) {
        this.postsList = posts
        notifyDataSetChanged()
    }

}