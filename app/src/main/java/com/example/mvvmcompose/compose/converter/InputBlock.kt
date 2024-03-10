package com.example.mvvmcompose.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmcompose.data.Conversion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    cal: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {

        Row(modifier = modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value, onValueChange = {
                    inputText.value = it
                },
                modifier = modifier.fillMaxWidth(fraction = 0.80F),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number

                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F)
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp)

            )

            Text(
                text = conversion.convertFrom,
                modifier = modifier
                    .padding(10.dp, 50.dp, 0.dp, 0.dp)
                    .fillMaxWidth(fraction = 0.35F)
            )

        }


        Spacer(modifier = modifier.height(20.dp))

        OutlinedButton(onClick = {

            if (inputText.value.isNotEmpty()) {

                cal(inputText.value)
                inputText.value = ""
                focusManager.clearFocus()
            } else {

                Toast.makeText(context, "Please, Enter Your Value", Toast.LENGTH_LONG).show()
            }
        }, modifier = modifier.fillMaxWidth(fraction = 1F)) {
            Text(
                text = "Convert",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }

}