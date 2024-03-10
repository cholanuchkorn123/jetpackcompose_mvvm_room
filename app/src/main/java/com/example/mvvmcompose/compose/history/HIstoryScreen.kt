package com.example.mvvmcompose.compose.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmcompose.data.ConversionResult

@Composable
fun HistoryScreen(
    navController: NavController,
    list: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onClose: (ConversionResult) -> Unit,
    onClearAll: () -> Unit
) {
    if (list.value.isNotEmpty()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    bottom = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "History")

            OutlinedButton(onClick = { onClearAll() }) {
                Text(text = "Clear All", color = Color.Gray)
            }
        }
    }
    HistoryList(navController, list = list, onCloseTask = {

            item ->
        onClose(item)

    })
}