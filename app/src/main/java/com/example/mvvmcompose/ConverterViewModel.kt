package com.example.mvvmcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcompose.data.Conversion
import com.example.mvvmcompose.data.ConversionResult
import com.example.mvvmcompose.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(private val repo: ConverterRepository) : ViewModel() {

    fun getConversion() = listOf<Conversion>(

        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers to Miles", "km", "mi", 0.621371)
    )

    fun addResult(msg1: String, msg2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertResult(ConversionResult(0, msg1, msg2))
        }
    }

    val resultList = repo.getSavedResult()

    fun removeResult(item: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteResult(item)
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllResult()
        }
    }
}