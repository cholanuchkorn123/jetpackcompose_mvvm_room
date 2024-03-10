package com.example.mvvmcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.mvvmcompose.data.ConverterRepository

class ConverterViewModelFactory(private val repo: ConverterRepository) :
    NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ConverterViewModel(repo) as T
}