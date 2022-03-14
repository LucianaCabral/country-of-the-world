package com.lcabral.countryapi.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryBinding
import com.lcabral.countryapi.viewmodel.CountryViewModel

class CountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryBinding
    private lateinit var viewModel: CountryViewModel
    private val countryAdapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.title_first_screen)

        initUI()
        setupViewModel()
        setupObservers()
        onClick(country = null)
    }

    private fun initUI() {
        binding.myReciclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.fetchCountries()
        viewModel.items.observe(this, Observer { countries ->
            countries?.let {
                countryAdapter.update(it)
                makeText(this, "success", LENGTH_LONG).show()
                loadingVisibility(false)
            }
        })
    }

    private fun loadingVisibility(isLoading: Boolean) {
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    fun onClick(country: View?) {
       country?.setOnClickListener {
            val intent = Intent(this,CountryDetailActivity::class.java)
            startActivity(intent)
        }
    }
}