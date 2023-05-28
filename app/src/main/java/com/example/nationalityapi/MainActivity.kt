package com.example.nationalityapi

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nationalityapi.databinding.ActivityMainBinding
import com.example.nationalityapi.network.ApiFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CountryAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

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
        binding.button.isEnabled = false
        val myName = binding.etName.text.toString()
        val list = ApiFactory.getApiService().getNationality(myName)
        adapter.submitList(list.country)
        binding.button.isEnabled = true
    }
}