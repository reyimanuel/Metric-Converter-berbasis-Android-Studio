package com.example.metricconverter.ui.temperatureConverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.metricconverter.databinding.FragmentTemperatureConverterBinding

class TemperatureConverterFragment : Fragment() {
    private lateinit var binding: FragmentTemperatureConverterBinding
    private val units = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTemperatureConverterBinding.inflate(inflater, container, false)

        setupSpinners()

        binding.convertButton.setOnClickListener {
            val input = binding.inputValue.text.toString().toDoubleOrNull()
            val fromUnit = binding.spinnerFrom.selectedItem.toString()
            val toUnit = binding.spinnerTo.selectedItem.toString()

            if (input != null) {
                val result = convertTemperature(input, fromUnit, toUnit)
                binding.resultText.text = "Result: $input $fromUnit = $result $toUnit"
            } else {
                binding.resultText.text = "Please enter a valid number"
            }
        }

        return binding.root
    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter
    }

    private fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double {
        return when (fromUnit to toUnit) {
            "Celsius" to "Fahrenheit" -> (value * 9 / 5) + 32
            "Celsius" to "Kelvin" -> value + 273.15
            "Fahrenheit" to "Celsius" -> (value - 32) * 5 / 9
            "Fahrenheit" to "Kelvin" -> (value - 32) * 5 / 9 + 273.15
            "Kelvin" to "Celsius" -> value - 273.15
            "Kelvin" to "Fahrenheit" -> (value - 273.15) * 9 / 5 + 32
            else -> value // If fromUnit and toUnit are the same
        }
    }
}