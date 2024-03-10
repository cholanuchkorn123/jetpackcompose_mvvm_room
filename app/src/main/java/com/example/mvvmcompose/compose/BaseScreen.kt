package com.example.mvvmcompose.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mvvmcompose.ConverterViewModel
import com.example.mvvmcompose.ConverterViewModelFactory
import com.example.mvvmcompose.compose.converter.TopScreen
import com.example.mvvmcompose.compose.history.HistoryScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun BaseScreen(
    viewModelFactory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    navController: NavController,
    converterViewModel: ConverterViewModel = viewModel(factory = viewModelFactory)
) {
    val list = converterViewModel.getConversion()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())


    Column(modifier = modifier.padding(30.dp)) {
        TopScreen(list, save = { msg1, msg2 ->
            converterViewModel.addResult(msg1 = msg1, msg2 = msg2)
        })
        Spacer(modifier = modifier.height(20.dp))

        HistoryScreen(navController, list = historyList, onClose = { item ->
            converterViewModel.removeResult(item)
        }, onClearAll = {
            converterViewModel.clearAll()
        })
    }
}