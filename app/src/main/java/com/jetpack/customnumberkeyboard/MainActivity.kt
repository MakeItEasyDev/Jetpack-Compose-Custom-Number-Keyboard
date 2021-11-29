package com.jetpack.customnumberkeyboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.customnumberkeyboard.ui.theme.CustomNumberKeyboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomNumberKeyboardTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CustomNumberKeyboard()
                }
            }
        }
    }
}

@Composable
fun CustomNumberKeyboard() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Custom Number Keyboard",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) {
        CustomKeyboard()
    }
}

@Composable
fun CustomKeyboard() {
    val inputVal = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    CustomKeyboard(
        input = inputVal.value,
        scrollState = scrollState,
        onClick = { digit ->
            inputVal.value += digit.toString()
        }
    )
}

@Composable
fun CustomKeyboard(
    input: String,
    scrollState: ScrollState,
    onClick: (digit: Char) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = input,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .weight(1f)
                .padding(4.dp),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 1, onClick = onClick, modifier = Modifier.weight(1f))
            NumberButton(number = 2, onClick = onClick, modifier = Modifier.weight(1f))
            NumberButton(number = 3, onClick = onClick, modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 4, onClick = onClick, modifier = Modifier.weight(1f))
            NumberButton(number = 5, onClick = onClick, modifier = Modifier.weight(1f))
            NumberButton(number = 6, onClick = onClick, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 7, onClick = onClick, modifier = Modifier.weight(1f))
            NumberButton(number = 8, onClick = onClick, modifier = Modifier.weight(1f))
            NumberButton(number = 9, onClick = onClick, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            NumberButton(number = 0, onClick = onClick, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun NumberButton(
    number: Int,
    onClick: (digit: Char) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = {
            @OptIn(ExperimentalStdlibApi::class)
            onClick(number.digitToChar())
        },
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        border = ButtonDefaults.outlinedBorder.copy(
            brush = SolidColor(MaterialTheme.colors.primary)
        )
    ) {
        Text(
            text = number.toString(),
            fontSize = 30.sp,
            color = MaterialTheme.colors.primary
        )
    }
}














