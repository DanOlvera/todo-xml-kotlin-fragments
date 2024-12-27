package com.example.kotlinxmltodo.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinxmltodo.R
import com.example.kotlinxmltodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Dang", "onCreate: ")


        if (savedInstanceState == null) {

            Log.d("Dang", "onCreate: MainActivity about to create fragment")
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.listContainer, PostsListFragment())
                .commit()
        }
    }
}