package com.lcabral.countryapi.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryDetailBinding
import com.lcabral.countryapi.data.model.Country
import com.lcabral.countryapi.data.model.Currency
import com.lcabral.countryapi.data.model.Flags
import com.lcabral.countryapi.viewmodel.CountryDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*
import kotlinx.parcelize.Parcelize

private const val ARGS_COUNTRY = "argsCountry"
class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding
    private val countryDetailsViewModel: CountryDetailViewModel by viewModel()
    private var country: Country? = null

    companion object {
        const val EXTRA_COUNTRY: String = "EXTRA_COUNTRY"

        fun getIntent(context: Context, args: CountryArgs): Intent {
            return Intent(context, CountryDetailActivity::class.java).apply {
                putExtra(ARGS_COUNTRY, args)
            }
        }
    }

    @Parcelize
    data class CountryArgs(
        val name: String,
        val flags: Flags,
        val flag: String,
        val capital: String,
        val population: Long,
        val region: String,
        val currency: List<Currency>,
        val area: String,
        val code:String,
        val nativeName: String
    ): Parcelable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.information_of_the_country)

        getExtras()
        initUI()
        this.setupBackButton()
    }

    override fun onResume() {
        super.onResume()
        countryDetailsViewModel.init()
    }

    private fun getExtras() {
        country = intent.getParcelableExtra(EXTRA_COUNTRY)
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    private fun initViews() {
        with(binding) {
            countryName.text = country?.name
            codeName.text = country?.code.toString()
            nativeName.text = String.format(
                resources.getString(R.string.nativeName_label), country?.nativeName
            )
            capital.text = String.format(
                resources.getString(R.string.capital_label), country?.capital
            )

            population.text = String.format(
                resources.getString(R.string.population_label),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(country?.population)
            )

            region.text = String.format(resources.getString(R.string.region_label), country?.region)
            area.text = String.format(resources.getString(R.string.area_label), country?.area)

            currencyName.text = String.format(
                resources.getString(R.string.currency_label_name),
                country?.currency?.joinToString { it.name }
            )
            currencyCode.text = String.format(
                resources.getString(R.string.currency_label_code),
                country?.currency?.joinToString { it.code }
            )
            currencySymbol.text = String.format(
                resources.getString(R.string.currency_label_symbol),
                country?.currency?.joinToString { it.symbol }
            )
        }
    }

    private fun initUI() {
        country?.let {
            Glide.with(binding.imgFlag.context).load(country?.flags?.png).into(binding.imgFlag)
            initViews()
        }
    }

    private fun setupBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}

