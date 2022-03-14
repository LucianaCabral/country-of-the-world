package com.lcabral.countryapi.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.countryapi.databinding.ItemListBinding
import com.lcabral.countryapi.model.Country

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val items: ArrayList<Country> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(countries: List<Country>) {
        items.clear()
        items.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = items[position]
        holder.bind(country)
//        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            itemView.apply {
//                setOnClickListener { listener.clickItemCountry(country) }
                binding.countryName.text = country.name
                binding.region.text = country.region
                Glide.with(binding.imgFlag.context).load(country.flags.png).into(binding.imgFlag)
            }
        }
    }
}

