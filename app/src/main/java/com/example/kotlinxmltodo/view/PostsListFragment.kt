package com.example.kotlinxmltodo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinxmltodo.R
import com.example.kotlinxmltodo.databinding.FragmentPostsListBinding
import com.example.kotlinxmltodo.model.data.Post
import com.example.kotlinxmltodo.viewmodel.PostViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostsListFragment : Fragment(), PostAdapter.PostClickListener {

    private lateinit var listBinding: FragmentPostsListBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Dang", "onCreate: ")
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d("Dang", "onCreateView: ")
        listBinding = FragmentPostsListBinding.inflate(layoutInflater, container, false)

        postAdapter = PostAdapter(emptyList(), this)

        listBinding.postsList.adapter = postAdapter
        listBinding.postsList.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel()

        return listBinding.root
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.posts.collectLatest { posts ->
                postAdapter.updatePosts(posts)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            PostsListFragment().apply {

            }
    }

    override fun onPostClick(post: Post) {
        val detailsFragment = PostDetailFragment.newInstance(post)
        childFragmentManager.beginTransaction().apply {
            replace(R.id.listContainer, detailsFragment)
            addToBackStack(null)
            commit()
        }
    }
}