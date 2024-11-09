package com.example.metricconverter.ui.lengthConverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.metricconverter.databinding.FragmentLengthConverterBinding

class LengthConverterFragment : Fragment() {
    private lateinit var binding: FragmentLengthConverterBinding
    private val units = arrayOf("mm", "cm", "dm", "m", "dam", "hm", "km")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use the updated layout file
        binding = FragmentLengthConverterBinding.inflate(inflater, container, false)

        setupSpinners()

        binding.convertButton.setOnClickListener {
            val input = binding.inputValue.text.toString().toDoubleOrNull()
            val fromUnit = binding.spinnerFrom.selectedItem.toString()
            val toUnit = binding.spinnerTo.selectedItem.toString()

            if (input != null) {
                val result = convertLength(input, fromUnit, toUnit)
                this.binding.resultText.text = "Result: $input $fromUnit = $result $toUnit"
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

    private fun convertLength(value: Double, fromUnit: String, toUnit: String): Double {
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

        return when (toUnit) {
            "mm" -> toMeters * 1000
            "cm" -> toMeters * 100
            "dm" -> toMeters * 10
            "m" -> toMeters
            "dam" -> toMeters / 10
            "hm" -> toMeters / 100
            "km" -> toMeters / 1000
            else -> toMeters
        }
    }
}