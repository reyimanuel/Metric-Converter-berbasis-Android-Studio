package com.example.metricconverter.ui.lengthConverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LengthConverterViewModel : ViewModel() {
    private val units = arrayOf("mm", "cm", "dm", "m", "dam", "hm", "km")

    // LiveData for observing results in the fragment
    private val _conversionResult = MutableLiveData<String>()
    val conversionResult: LiveData<String> = _conversionResult

    fun getUnits(): Array<String> = units

    fun convertLength(value: Double, fromUnit: String, toUnit: String) {
        val toMeters = when (fromUnit) {
            "mm" -> value / 1000
            "cm" -> value / 100
            "dm" -> value / 10
            "m" -> value
            "dam" -> value * 10
            "hm" -> value * 100
            "km" -> value * 1000
            else -> value
        }

        val result = when (toUnit) {
            "mm" -> toMeters * 1000
            "cm" -> toMeters * 100
            "dm" -> toMeters * 10
            "m" -> toMeters
            "dam" -> toMeters / 10
            "hm" -> toMeters / 100
            "km" -> toMeters / 1000
            else -> toMeters
        }

        _conversionResult.value = "$value $fromUnit = $result $toUnit"
    }
}