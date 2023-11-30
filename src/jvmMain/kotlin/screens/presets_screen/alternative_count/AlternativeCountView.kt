package screens.presets_screen.alternative_count

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import screens.elements.BasicButton
import screens.elements.Counter

@Composable
fun AlternativeCountView(
    alternativeCount:Int,
    onAddCounterAlternativeValue:(Int)->Unit,
    onRemoveCounterAlternativeValue:(Int)->Unit,
    onAlternativeButtonAction:()->Unit
){
    Surface(
        shape = RoundedCornerShape(size = 5.dp),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier.padding(15.dp),
        color = Color.Transparent
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Alternatives", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Counter("Quantity: ", alternativeCount,
                onAddCounterValue = onAddCounterAlternativeValue,
                onRemoveCounterValue = onRemoveCounterAlternativeValue
            )
            Spacer(modifier = Modifier.height(5.dp))
            BasicButton("Settings", action = onAlternativeButtonAction)
        }
    }
}