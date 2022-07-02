package com.lcabral.countryapi.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryBinding
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_AREA
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_CAPITAL
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_CODE
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_CURRENCY_NAM
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_FLAGS
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_NAME
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_NATIVE_NAME
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_POPULATION
import com.lcabral.countryapi.presentation.CountryDetailActivity.Companion.EXTRA_REGION
import com.lcabral.countryapi.viewmodel.CountryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CountryActivity : AppCompatActivity(), ItemClickListenerCountry {
    private lateinit var binding: ActivityCountryBinding
    private val viewModel: CountryViewModel by viewModel()
    private var countryAdapter = CountryAdapter(this)

    companion object {
        const val EXTRA_COUNTRY = "EXTRA_COUNTRY"
        const val TAG = "Tag"
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
        viewModel.items.observe(this, Observer { countries ->
            countries?.let {
                if (countries.isNotEmpty()) {
                    updateList(it)
                    Toast.makeText(this, "success", LENGTH_LONG).show()
                    loadingVisibility(false)
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

    override fun itemClickCountry(country: Country) {
        extras(country)
    }

    private fun extras(country: Country) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        country.apply {
            intent.putExtra(EXTRA_COUNTRY, country)
            intent.putExtra(EXTRA_FLAGS, flags.svg)
            intent.putExtra(EXTRA_NAME, name)
            intent.putExtra(EXTRA_NATIVE_NAME, nativeName)
            intent.putExtra(EXTRA_CAPITAL, capital)
            intent.putExtra(EXTRA_POPULATION, population)
            intent.putExtra(EXTRA_REGION, region)
            intent.putExtra(EXTRA_CURRENCY_NAM, currency.toString())
            intent.putExtra(EXTRA_AREA, area)
            intent.putExtra(EXTRA_CODE, code)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            makeText(this, "Save item", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.delete -> {
            makeText(this, "deletes", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.search_action)
        val searchView = searchItem.actionView as SearchView

        searchView.imeOptions = IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                countryAdapter.filter.filter(query)
                return true
            }
        })
       searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
           override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
               countryAdapter.filter.filter("")
               makeText(this@CountryActivity, "Action Collapse", Toast.LENGTH_SHORT).show()
               return true
           }

           override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
               makeText(this@CountryActivity, "Action Expand", Toast.LENGTH_SHORT).show()
               return true
           }
    })
        return super.onCreateOptionsMenu(menu)
    }

//    private fun getCountries(): MutableList<Country> {
//        val mdList = mutableListOf<Country>()
//        for(countryISO in Locale.getISOCountries()) {
//            val locale = Locale("", countryISO)
//            if(locale.displayCountry.isNotEmpty()) {
//                mdList.add(Country(locale.displayCountry + countryFlag(countryISO)))
//            }
//        }
//        return mdList
//    }
////
//        val flagOffset = 0x1F1E6
//        val asciiOffset = 0x41
//        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
//        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
//        return (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
//    }
}




