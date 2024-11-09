package com.example.metricconverter.ui.massConverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.metricconverter.databinding.FragmentMassConverterBinding

class MassConverterFragment : Fragment() {
    private lateinit var binding: FragmentMassConverterBinding
    private val units = arrayOf("mg", "cg", "dg", "g", "dag", "hg", "kg")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMassConverterBinding.inflate(inflater, container, false)

        setupSpinners()

        binding.convertButton.setOnClickListener {
            val input = binding.inputValue.text.toString().toDoubleOrNull()
            val fromUnit = binding.spinnerFrom.selectedItem.toString()
            val toUnit = binding.spinnerTo.selectedItem.toString()

            if (input != null) {
                val result = convertMass(input, fromUnit, toUnit)
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

    private fun convertMass(value: Double, fromUnit: String, toUnit: String): Double {
        val toGrams = when (fromUnit) {
            "mg" -> value / 1000
            "cg" -> value / 100
            "dg" -> value / 10
            "g" -> value
            "dag" -> value * 10
            "hg" -> value * 100
            "kg" -> value * 1000
            else -> value
        }

        return when (toUnit) {
            "mg" -> toGrams * 1000
            "cg" -> toGrams * 100
            "dg" -> toGrams * 10
            "g" -> toGrams
            "dag" -> toGrams / 10
            "hg" -> toGrams / 100
            "kg" -> toGrams / 1000
            else -> toGrams
        }
    }
}