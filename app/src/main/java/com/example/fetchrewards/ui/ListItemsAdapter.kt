package com.example.fetchrewards.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.data.vo.GroupedItem
import com.example.fetchrewards.data.vo.ListItem
import com.example.fetchrewards.databinding.ItemHeaderBinding
import com.example.fetchrewards.databinding.ItemListBinding

class ListItemsAdapter(private val groupedItems: List<GroupedItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }
    override fun getItemViewType(position: Int): Int {
        return if (groupedItems[position].items.isEmpty()) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val group = groupedItems[position]
                holder.binding.ListId.text = "List ID: ${group.listId}"
            }
            is ItemViewHolder -> {
                val item = groupedItems[position].items[position]
                holder.binding.Name.text = item.name
            }
        }
    }

    override fun getItemCount(): Int = groupedItems.size

    class HeaderViewHolder(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)
}
