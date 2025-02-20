package com.example.fetchrewards.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.R
import com.example.fetchrewards.data.vo.datalist

class ListItemsAdapter(private val listItems: List<datalist>) : RecyclerView.Adapter<ListItemsAdapter.ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val items = listItems[position]
        holder.id.text = items.id.toString()
        holder.listId.text = items.listId.toString()
        holder.name.text = items.name.toString()
    }

    override fun getItemCount(): Int = listItems.size

    inner class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val listId: TextView = itemView.findViewById(R.id.listId)
        val name: TextView = itemView.findViewById(R.id.name)
    }
}
