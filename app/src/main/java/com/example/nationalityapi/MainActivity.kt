package com.example.nationalityapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nationalityapi.databinding.ActivityMainBinding
import com.example.nationalityapi.network.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                loadNationality()
            }
        }
    }

    private suspend fun loadNationality() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
        binding.button.isEnabled = false
//        CoroutineScope(Dispatchers.IO).launch {
        val api = ApiFactory.getApiService().getNationality()
//            runOnUiThread {
//                binding.apply {
        adapter.submitList(api.country)
//                }
//            }
//        }
        binding.chronometer.stop()
        binding.button.isEnabled = true
    }
}