package com.lcabral.countryapi.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.countryapi.databinding.ItemListBinding
import com.lcabral.countryapi.model.Country

class CountryAdapter(private val onItemClickListenerCountry: ItemClickListenerCountry) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private val countryList = ArrayList<Country>()
    var countryFilterList = countryList

    init {
        countryFilterList = ArrayList(countryList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(countries: List<Country>) {
        countryList.clear()
        countryList.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countryList[position]
        holder.bind(country, onItemClickListenerCountry)
    }

    override fun getItemCount(): Int = countryList.size

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country, onItemClickListenerCountry: ItemClickListenerCountry) {
            itemView.apply {
                setOnClickListener { onItemClickListenerCountry.itemClickCountry(country) }
                with(binding) {
                    countryName.text = country.name.toString()
                    region.text = country.region.toString()
                    Glide.with(imgFlag.context).load(country.flags.png)
                        .into(imgFlag)
                }
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val searchText = constraint.toString().lowercase()
                countryFilterList = if (searchText.isNotEmpty()) {
                    countryList
                } else {
                    val resultList = ArrayList<Country>()
                    countryList.forEach { country ->
                        country.name?.let { countryName ->
                            if (countryName.isNotEmpty()) {
                                countryName.lowercase().contains(searchText)
                                    resultList.add(country)
                            }
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraints: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Country>
                notifyDataSetChanged()
            }
        }
    }
}

