package com.lcabral.countryapi.utils

import android.content.Context
import com.lcabral.countryapi.R
import kotlin.math.ln
import kotlin.math.pow

fun formatPopulation(population: Long, context: Context): String {

    if (population < 1000) return population.toString()

    val exp = (ln(population.toDouble()) / ln(1000.0)).toInt()
    val suffix = context.resources.getStringArray(R.array.population_suffices)

    return String.format("%.1f %s",
        population / 1000.0.pow(exp.toDouble()),
        suffix[exp - 1])
}


