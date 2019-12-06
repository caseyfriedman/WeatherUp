package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.assignment3.adapter.CitiesAdapter
import com.example.assignment3.api.WeatherAPI
import com.example.assignment3.data.Base
import com.example.assignment3.touch.RecyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.activity_scrolling.view.*
import kotlinx.android.synthetic.main.list_row.*


class ScrollingActivity : AppCompatActivity(), CityDialog.CityHandler {


    companion object{
        var TAG_CITY_DIALOG = "TAG_CITY_DIALOG"
    }


    lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        initRecyclerView()

        fab.setOnClickListener {
            showAddCityDialog()
        }


    }


    private fun initRecyclerView() {


        citiesAdapter = CitiesAdapter(this)
        recyclerCity.adapter = citiesAdapter

        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerCity.addItemDecoration(itemDecorator)


        val callback = RecyclerTouchCallback(citiesAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerCity)

    }


    private fun showAddCityDialog() {
        CityDialog().show(supportFragmentManager, TAG_CITY_DIALOG)
    }

    override fun cityCreated(string: String) {
        citiesAdapter.addItem(string)
    }

}