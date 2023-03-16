package com.example.nationalityapi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nationalityapi.databinding.ItemCountriesBinding
import com.example.nationalityapi.models.Country

class CountryAdapter : ListAdapter<Country, CountryAdapter.CountryViewHolder>(Comparator()) {

    private var countryList: List<Country> = listOf()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountriesBinding.bind(view)
        fun bind(country: Country) {
            binding.tvCountry.text = country.country_id
            binding.tvProbability.text = country.probability.toString()
        }
    }

    class Comparator : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.country_id == newItem.country_id
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(countryViewHolder: CountryViewHolder, position: Int) {
        countryViewHolder.bind(getItem(position))
    }

}