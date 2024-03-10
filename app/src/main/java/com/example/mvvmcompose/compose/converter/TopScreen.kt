package com.example.mvvmcompose.compose.converter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mvvmcompose.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    save: (String, String) -> Unit
) {
    //  rx in dart RxString selectedConversion  ->
    val selectedConversion: MutableState<Conversion?> = remember {
        mutableStateOf(null)
    }

    val inputString: MutableState<String> = remember {
        mutableStateOf("")
    }

    val typedValue = remember {
        mutableStateOf("0.0")
    }
    ConversionMenu(list = list, convert = {
        selectedConversion.value = it
        typedValue.value = "0.0"
    })

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputString, cal = { input ->
            typedValue.value = input
        })
    }

    if (typedValue.value != "0.0") {
        val input = typedValue.value.toDouble()
        val multiply = selectedConversion.value!!.multipleBy

        val result = input * multiply

        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.DOWN
        val roundedResult = df.format(result)

        val msg1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
        val msg2 = "${roundedResult} ${selectedConversion.value!!.convertTo}"
        save(msg1, msg2)
        ResultBlock(msg1 = msg1, msg2 = msg2)
    }

}