package com.example.nationalityapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nationalityapi.databinding.ItemCountriesBinding
import com.example.nationalityapi.models.Country
import java.util.*

class CountryAdapter(val context: Context) :
    ListAdapter<Country, CountryAdapter.ViewHolder>(Comparator()) {

    val countryNameString = context.resources.getString(R.string.country)
    val probability = context.resources.getString(R.string.probability)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountriesBinding.bind(view)
        fun bind(country: Country) {
            val countryCode = country.countryId
            val locale = countryCode?.let { Locale("", it) }
            val countryName = locale?.displayCountry

            binding.tvCountryApi.text = countryName
            binding.tvProbabilityApi.text = country.probability.toString()
            binding.tvCountry.text = countryNameString
            binding.tvProbability.text = probability
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