package com.example.fetchrewards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewards.data.api.ApiService
import com.example.fetchrewards.data.vo.ListItem
import com.example.fetchrewards.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListItemsAdapter
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        itemViewModel.groupedItems.observe(this, Observer { groupedItems ->

            adapter = ListItemsAdapter(groupedItems)
            binding.recyclerview.layoutManager = LinearLayoutManager(this)
            binding.recyclerview.adapter = adapter
        })

        itemViewModel.fetchData()
    }
}