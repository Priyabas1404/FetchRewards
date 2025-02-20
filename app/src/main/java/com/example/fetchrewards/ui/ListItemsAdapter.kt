package com.example.fetchrewards.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.data.vo.DataList
import com.example.fetchrewards.databinding.ItemListBinding

class ListItemsAdapter(private val listItems: List<DataList>) : RecyclerView.Adapter<ListItemsAdapter.ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    inner class ItemsViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(DataList: DataList) {
            binding.id.text = DataList.id.toString()
            binding.listId.text = DataList.listId.toString()
            binding.name.text = DataList.name.toString()
        }
    }
}
