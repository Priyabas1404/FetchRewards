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
    override fun getItemCount(): Int {
        return  return groupedItems.sumOf { it.items.size } + groupedItems.size
    }
    override fun getItemViewType(position: Int): Int {
        var currentPos = position
        for (group in groupedItems) {
            if (currentPos == 0) return VIEW_TYPE_HEADER
            currentPos -= group.items.size + 1
        }
        return VIEW_TYPE_ITEM
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            VIEW_TYPE_ITEM -> {
                val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var groupPosition = 0
        var itemPosition = position

        for (i in groupedItems.indices) {
            val group = groupedItems[i]
            if (itemPosition < group.items.size) {
                groupPosition = i
                break
            } else {
                itemPosition -= group.items.size
            }
        }

        when (holder) {
            is HeaderViewHolder -> {
                val group = groupedItems[groupPosition]
                holder.binding.ListId.text = "List ID: ${group.listId}"
            }
            is ItemViewHolder -> {
                val item = groupedItems[groupPosition].items[itemPosition]
                holder.binding.Name.text = item.name
            }
        }
        }
    class HeaderViewHolder(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)
}