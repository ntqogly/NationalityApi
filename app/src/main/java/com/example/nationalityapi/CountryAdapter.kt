package com.example.nationalityapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nationalityapi.databinding.ItemCountriesBinding
import com.example.nationalityapi.models.Country
import java.text.NumberFormat
import java.util.*

class CountryAdapter() : ListAdapter<Country, CountryAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountriesBinding.bind(view)

        fun bind(country: Country) {
            val countryCode = country.country_id
            val locale = countryCode?.let { Locale("", it) }
            val countryName = locale?.displayCountry

            val defaultFormat = NumberFormat.getPercentInstance()
            defaultFormat.minimumFractionDigits
            val percentageProbability = defaultFormat.format(country.probability)

            binding.apply {
                tvCountryApi.text = countryName
                tvProbabilityApi.text = percentageProbability.toString()
                tvCountry.text = "Страна:"
                tvProbability.text = "Вероятность:"
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

}