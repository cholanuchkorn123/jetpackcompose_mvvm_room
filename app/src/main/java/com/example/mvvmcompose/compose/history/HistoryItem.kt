package com.example.mvvmcompose.compose.history

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HistoryItem(
    msg1: String,
    msg2: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(border = BorderStroke(1.dp, color = Color.Gray))
            .padding(16.dp)
            .clickable {
                try {
                    navController.navigate("details_screen/${msg1}/${msg2}")
                } catch (e: Exception) {
                    Log.e("NavigationError", "Error navigating to details screen", e)
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column {

            Text(text = msg1, fontSize = 20.sp)

            Text(
                text = msg2, color = Color.Blue, fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }
        IconButton(onClick = {

            onClose()
        }) {

            Icon(Icons.Filled.Close, contentDescription = "close")
        }
    }
}