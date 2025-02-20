package com.example.fetchrewards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.data.api.ApiService
import com.example.fetchrewards.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fetch data from the URL using Retrofit
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val items = withContext(Dispatchers.IO) {
                    apiService.getListItems()
                }

                if (items.isNotEmpty()) {
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    val adapter = ListItemsAdapter(items)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "No items found", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}