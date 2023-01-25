package com.ugrcaan.learningapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ugrcaan.learningapi.R
import com.ugrcaan.learningapi.adapter.RecyclerViewAdapter
import com.ugrcaan.learningapi.databinding.ActivityMainBinding
import com.ugrcaan.learningapi.model.CurrencyModel
import com.ugrcaan.learningapi.service.CurrencyAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var currencyModels : ArrayList<CurrencyModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CurrencyModel>> {
            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        currencyModels = ArrayList(it)

                        currencyModels.let {
                            recyclerViewAdapter =
                                RecyclerViewAdapter(it as ArrayList<CurrencyModel>)
                            recyclerView.adapter = recyclerViewAdapter
                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}