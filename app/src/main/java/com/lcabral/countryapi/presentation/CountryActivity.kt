package com.lcabral.countryapi.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryBinding
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.viewmodel.CountryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryActivity : AppCompatActivity(), ItemClickListenerCountry {
    private lateinit var binding: ActivityCountryBinding
    private val viewModel: CountryViewModel by viewModel()
    private var countryAdapter = CountryAdapter(this)


    companion object {
        const val EXTRA_COUNTRY = "EXTRA_COUNTRY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.title_first_screen)

        initUI()
    }

    private fun initUI() {
        binding.myReciclerview.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onStart() {
        super.onStart()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                if (countries.isNotEmpty()) {
                    updateList(it)
                    Toast.makeText(this, "success", LENGTH_LONG).show()
                    loadingVisibility(false)
                    searchListDisplay(it)
                } else{
                    throw Exception("error")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

    private fun updateList(items: List<Country>) {
        countryAdapter.updateAdapter(items)
    }

    private fun loadingVisibility(isLoading: Boolean) {
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun itemClickListenerCountry(country: Country) {
        extras(country)
    }

    private fun extras(country: Country) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        country.apply {
            intent.putExtra(EXTRA_COUNTRY, country)
            startActivity(intent)
        }
    }

    private fun searchListDisplay(items: List<Country>) {
        binding.searchAction.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                resultListSearch(query,items)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                resultListSearch(newText,items)
                return false
            }
        })
    }

    private fun resultListSearch(search: String, items: List<Country>) {
        val listResultSearch: MutableList<Country> = arrayListOf()
        for (country in items) {
            if (country.name!!.contains(search, ignoreCase = true)) {
                listResultSearch.add(country)
            }
        }
        updateList(listResultSearch)
    }
}