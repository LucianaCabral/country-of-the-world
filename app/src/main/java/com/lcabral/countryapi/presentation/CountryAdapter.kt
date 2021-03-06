package com.lcabral.countryapi.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.countryapi.databinding.ItemListBinding
import com.lcabral.countryapi.model.Country

class CountryAdapter(private val onItemClickListenerCountry: ItemClickListenerCountry) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val countryList = ArrayList<Country>()

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
                setOnClickListener {
                    with(receiver = onItemClickListenerCountry) {
                        itemClickListenerCountry(country)
                    }
                }
                with(binding) {
                    binding.region.text = country.region.toString()
                    countryName.text = country.name.toString()
                    Glide.with(imgFlag.context).load(country.flags.png).into(imgFlag)
                }
            }
        }
    }
}

