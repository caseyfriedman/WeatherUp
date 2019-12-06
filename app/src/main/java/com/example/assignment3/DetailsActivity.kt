package com.example.assignment3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.assignment3.api.WeatherAPI
import com.example.assignment3.data.Base
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {


    companion object {
        var CITY = "CITY"
    }

    private var city = ""
    private var units = "imperial" // for some reason using a string resource here makes it crash
    //attempt to invoke a virtual method 'android.content.res.Resources on a null object reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        city = intent.getStringExtra(CITY)


        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherAPI = retrofit.create(WeatherAPI::class.java)
        val call = weatherAPI.getWeatherDetails(

            city,
            units,
            getString(R.string.appid)
        )

        call.enqueue(object : Callback<Base> {
            override fun onResponse(call: Call<Base>, response: Response<Base>) {

                if (response.isSuccessful) {
                    val response = response.body()
                    setLayout(response)
                } else {
                    Toast.makeText(this@DetailsActivity, "Invalid City", Toast.LENGTH_LONG).show()
                    finish()
                }

            }

            override fun onFailure(call: Call<Base>, t: Throwable) {

                Log.i("FAILED", "YEAH IT FAILED")

            }
        })

    }

    fun setLayout(response: Base?) {

        val icon = response?.weather?.get(0)?.icon
        val url = "https://openweathermap.org/img/w/"
        val fullUrl = "$url$icon.png"


        Log.i("ICON", icon)





        tvCity.text = city

        tvHigh.text = String.format(
            getString(R.string.high),
            response?.main?.temp_max.toString().substringBefore('.'),
            getString(R.string.fahrenheit_symbol)
        )


        tvLow.text = String.format(
            getString(R.string.low),
            response?.main?.temp_min.toString().substringBefore('.'),
            getString(R.string.fahrenheit_symbol)
        )//also now ever since  I

        tvTemperature.text = String.format(
            response?.main?.temp.toString().substringBefore('.'),getString(R.string.fahrenheit_symbol)
        )


        tvMain.text = response?.weather?.get(0)?.main.toString()

        Glide.with(this).load(fullUrl).into(ivIcon)
    }
}
