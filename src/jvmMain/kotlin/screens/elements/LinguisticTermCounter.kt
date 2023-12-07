package screens.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun Counter(
    name: String,
    counterValue: Int,
    onAddCounterValue: (Int) -> Unit,
    onRemoveCounterValue: (Int) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = Modifier.width(100.dp).padding(end = 5.dp),
            text = name,
            style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
        )
        Surface(
            color = Color.Transparent
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier
                        .width(50.dp)
                        .background(Color.Transparent)
                        .padding(start = 2.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black,
                    ),
                    onClick = { onRemoveCounterValue(counterValue) }
                ) {
                    Text(text = "—", style = TextStyle(fontSize = 16.sp), textAlign = TextAlign.Center)
                }

                Text(
                    modifier = Modifier.width(40.dp),
                    text = counterValue.toString(),
                    style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center)
                )
                Button(
                    modifier = Modifier
                        .width(50.dp)
                        .background(Color.Transparent)
                        .padding(end = 2.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black,
                    ),
                    onClick = { onAddCounterValue(counterValue) }
                ){
                    Text(text = "+", style = TextStyle(fontSize = 16.sp), textAlign = TextAlign.Center)
                }
            }
        }
    }

}

@Composable
fun CounterV(
    name: String,
    counterValue: Float,
    onAddCounterValue: (Float) -> Unit,
    onRemoveCounterValue: (Float) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = Modifier.width(100.dp).padding(end = 5.dp),
            text = name,
            style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = Color.Transparent
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier
                        .width(50.dp)
                        .background(Color.Transparent)
                        .padding(start = 2.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black,
                    ),
                    onClick = { onRemoveCounterValue(counterValue) }
                ) {
                    Text(text = "—", style = TextStyle(fontSize = 16.sp), textAlign = TextAlign.Center)
                }

                Text(
                    modifier = Modifier.width(40.dp),
                    text = counterValue.toString(),
                    style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center)
                )
                Button(
                    modifier = Modifier
                        .width(50.dp)
                        .background(Color.Transparent)
                        .padding(end = 2.dp),
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black,
                    ),
                    onClick = { onAddCounterValue(counterValue) }
                ){
                    Text(text = "+", style = TextStyle(fontSize = 16.sp), textAlign = TextAlign.Center)
                }
            }
        }
    }

}
