package com.example.fetchrewards.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.data.vo.GroupedItem
import com.example.fetchrewards.databinding.ItemGroupBinding

class ListItemsAdapter(private val groupedItems: List<GroupedItem>) : RecyclerView.Adapter<ListItemsAdapter.GroupViewHolder>() {

    override fun getItemCount(): Int {
        return groupedItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groupedItems[position]

        holder.binding.ListId.text = "List ID: ${group.listId}"

        holder.binding.ItemContainer.removeAllViews()

        for (item in group.items) {
            val itemTextView = TextView(holder.binding.root.context)
            itemTextView.text = item.name
            itemTextView.setPadding(0, 8, 0, 8)
            holder.binding.ItemContainer.addView(itemTextView)
        }
    }
    class GroupViewHolder(val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root)
}