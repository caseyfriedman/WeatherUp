package com.example.assignment3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R
import com.example.assignment3.ScrollingActivity
import kotlinx.android.synthetic.main.list_row.view.*


class CitiesAdapter : RecyclerView.Adapter<CitiesAdapter.ViewHolder> {


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
        todoRow.setOnClickListener {
            Toast.makeText(context as ScrollingActivity, "Toast from 2", Toast.LENGTH_LONG).show()
        }

        return ViewHolder(todoRow)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = itemList[holder.adapterPosition]

        holder.tvTitle.text = item //set title

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


}

