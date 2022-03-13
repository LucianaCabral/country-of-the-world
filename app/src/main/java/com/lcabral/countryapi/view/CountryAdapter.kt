package com.lcabral.countryapi.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.countryapi.databinding.ItemListBinding
import com.lcabral.countryapi.model.Country

class CountryAdapter(private val items: ArrayList<Country>):
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

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
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

//    class ViewHolder(binding: ItemListBinding, var listener: ClickItemListenerCountry,var items: ArrayList<Country>) : RecyclerView.ViewHolder(binding.root) {
    class ViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

//        init {
//            binding.countryName.setOnClickListener {
//                listener.clickItemCountry(items[adapterPosition])
//            }
//        }
        private val countryName: TextView = binding.countryName
        private val region: TextView = binding.region
        private val imageFlag: ImageView = binding.imgFlag

        fun bind(country: Country) {
            countryName.text = country.name
            region.text = country.region
            Glide.with(imageFlag.context).load(country.flags.png).into(imageFlag)
        }
    }
}

