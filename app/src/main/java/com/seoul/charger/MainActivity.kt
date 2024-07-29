package com.seoul.charger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoul.charger.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChargerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChargerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fetchChargerData()
    }

    private fun fetchChargerData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitApi.chargerService.getChargers()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val citydata = response.body()?.cITYDATA
                        if (citydata != null && citydata.cHARGERSTTS != null) {
                            adapter.submitList(citydata.cHARGERSTTS)
                        } else {
                            Log.e("MainActivity", "API Response citydata or charger data is null")
                        }
                    } else {
                        Log.e("MainActivity", "API Error: ${response.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Network Error: ${e.message}")
            }
        }
    }
}
