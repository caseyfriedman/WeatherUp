package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.assignment3.adapter.CitiesAdapter
import com.example.assignment3.api.WeatherAPI
import com.example.assignment3.data.Base
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.list_row.*
import kotlinx.android.synthetic.main.new_city_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScrollingActivity : AppCompatActivity(), CityDialog.CityHandler {


    lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        initRecyclerView()

        fab.setOnClickListener {
            showAddCityDialog()
        }


        card_view?.setOnClickListener {
            Toast.makeText(this@ScrollingActivity, "fuck", Toast.LENGTH_LONG).show()
        }


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var weatherAPI = retrofit.create(WeatherAPI::class.java)
        val call = weatherAPI.getWeatherDetails(
            //etCity.text.toString()
            "Budapest",
            "metric",
            "e51537f1293f63d26aa8a5b8bd5a9b80"
        )

        call.enqueue(object : Callback<Base> {
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                val response = response.body()
                response?.weather
                val intent = Intent(this@ScrollingActivity, DetailsActivity::class.java).apply {
                    putExtra("WEATHER", response)
                }

                Log.i("SUCCESS","${response?.weather}")

            }

            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.i("FAILED","YEAH IT FAILED")
            }
        })

    }

    private fun initRecyclerView() {


        citiesAdapter = CitiesAdapter(this)
        recyclerCity.adapter = citiesAdapter

        var itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerCity.addItemDecoration(itemDecorator)

    }


    fun showAddCityDialog() {
        CityDialog().show(supportFragmentManager, "TAG_CITY_DIALOG")
    }

    override fun cityCreated(string: String) {
        citiesAdapter.addItem(string)
    }

}