package com.lcabral.countryapi.data

data class CountryeItem(
    val area: Double,
    val borders: List<String>,
    val capital: String,
    val currencies: List<Currency>,
    val flag: String,
    val flags: Flags,
    val languages: List<Language>,
    val name: String,
    val population: Int,
    val region: String,
)