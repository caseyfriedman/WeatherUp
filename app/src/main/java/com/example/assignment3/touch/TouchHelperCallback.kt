package com.example.assignment3.touch


import java.text.FieldPosition

interface TouchHelperCallback {

    fun onDismissed(position: Int)
    fun onItemMoved(fromPosition: Int, toPosition: Int)


}