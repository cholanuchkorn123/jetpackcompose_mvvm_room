package com.example.mvvmcompose.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mvvmcompose.data.ConversionResult

@Composable
fun HistoryList(
    navController: NavController,
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(content = {
        items(items = list.value,
            key = { item -> item.id }) { item ->
            HistoryItem(msg1 = item.messagePart1, msg2 = item.messagePart2, onClose = {

                onCloseTask(item)
            }, navController = navController)
        }
    })
}