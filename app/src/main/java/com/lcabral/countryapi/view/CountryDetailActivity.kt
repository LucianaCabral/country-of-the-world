package com.lcabral.countryapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryDetailBinding

class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.information_of_the_country)
    }
}