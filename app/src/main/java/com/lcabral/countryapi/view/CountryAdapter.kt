package com.lcabral.countryapi.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.countryapi.databinding.ItemListBinding
import com.lcabral.countryapi.model.Country
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(private val onItemClickListenerCountry: ItemClickListenerCountry) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private var items: ArrayList<Country> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(countries: List<Country>) {
        items.clear()
        items.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = items[position]
        holder.bind(country, onItemClickListenerCountry)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country, onItemClickListenerCountry: ItemClickListenerCountry) {
            itemView.apply {
                setOnClickListener { with(onItemClickListenerCountry) { itemClickCountry(country = country) } }
                with(binding) {
                    countryName.text = country.name.toString()
                    region.text = country.region.toString()
                    Glide.with(imgFlag.context).load(country.flags.png)
                        .into(imgFlag)
                }
            }
        }
    }

    private val countryFilter: Filter = object : Filter() {
        init {
            items = ArrayList(items)
        }

        override fun performFiltering(constrait: CharSequence?): FilterResults {
            val filteredList: MutableList<Country> = ArrayList()
            if (constrait == null || constrait.isEmpty()) {
                filteredList += items
            } else {
                val filterPattern =
                    constrait.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in items) {
                    if (item.name?.lowercase(Locale.getDefault())
                            ?.contains(filterPattern) == true
                    ) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constrait: CharSequence?, results: FilterResults?) {
            items.clear()
            if (results != null) {
                items.addAll(results.values as List<Country>)
            }
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        countryFilter.convertResultToString(items)
        return countryFilter
    }
}

