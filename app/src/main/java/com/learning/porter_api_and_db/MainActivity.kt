package com.learning.porter_api_and_db

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.porter_api_and_db.api.DataViewModel
import com.learning.porter_api_and_db.api.PostAdapter
import com.learning.porter_api_and_db.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : DataViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.data.observe(this, Observer { data ->
            postAdapter = data?.let { PostAdapter(it) }!!
            recyclerView.adapter = postAdapter
            Log.d("sucess message", data.toString())
        })

        viewModel.error.observe(this, Observer { error ->
            Log.d("sucess message", error.toString())

        })
        viewModel.getData()

    }
}