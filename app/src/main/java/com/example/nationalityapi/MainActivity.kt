package com.example.nationalityapi

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nationalityapi.databinding.ActivityMainBinding
import com.example.nationalityapi.network.ApiFactory
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CountryAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CountryAdapter()
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.adapter = adapter

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                buttonLoad()
            }
        }
    }


    private suspend fun buttonLoad() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
        binding.button.isEnabled = false
        loadNationality()
        binding.chronometer.stop()
        binding.button.isEnabled = true
    }

    private suspend fun loadNationality() {
        val list = ApiFactory.getApiService().getNationality()
        binding.apply {
            adapter.submitList(list.country)
        }
    }
}