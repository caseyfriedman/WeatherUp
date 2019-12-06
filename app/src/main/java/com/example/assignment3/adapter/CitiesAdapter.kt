package com.example.assignment3.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.DetailsActivity
import com.example.assignment3.R
import com.example.assignment3.ScrollingActivity
import com.example.assignment3.touch.RecyclerTouchCallback
import com.example.assignment3.touch.TouchHelperCallback
import kotlinx.android.synthetic.main.list_row.view.*
import java.util.*


class CitiesAdapter : RecyclerView.Adapter<CitiesAdapter.ViewHolder>, TouchHelperCallback {

    var itemList = mutableListOf<String>()
    var context: Context

    constructor(context: Context) {

        this.context = context

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var todoRow = LayoutInflater.from(context).inflate(
            R.layout.list_row, parent, false
        )


        return ViewHolder(todoRow)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = itemList[holder.adapterPosition]
        val intent = Intent(context as ScrollingActivity, DetailsActivity::class.java)


        holder.tvTitle.text = item //set title

        intent.putExtra("CITY", item)


        holder.itemView.setOnClickListener {
            startActivity(context, intent, null)
        }

    }


    fun deleteItem(index: Int) {
        itemList.removeAt(index)
        notifyItemRemoved(index)


    }

    fun addItem(string: String) {
        itemList.add(string)
        notifyItemInserted(itemList.lastIndex)
    }


    fun deleteAllItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onDismissed(position: Int) {
        deleteItem(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(itemList, fromPosition, toPosition)
        notifyItemMoved(fromPosition,toPosition)
    }


}

