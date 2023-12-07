package screens.presets_screen.criteria_count

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import screens.elements.BasicButton
import screens.elements.Counter

@Composable
fun CriteriaCountView(
    criteriaCount:Int,
    onAddCounterCriteriaValue:(Int)->Unit,
    onRemoveCounterCriteriaValue:(Int)->Unit,
    onCriteriaButtonAction:()->Unit
){
    Surface(
        shape = RoundedCornerShape(size = 5.dp),
        modifier = Modifier.padding(15.dp),
        color = Color.Transparent
    ) {
        Column(modifier = Modifier.padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Критерії", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Counter("Кількість: ", criteriaCount,
                onAddCounterValue = onAddCounterCriteriaValue,
                onRemoveCounterValue = onRemoveCounterCriteriaValue
            )
            Spacer(modifier = Modifier.height(5.dp))
            BasicButton("Налаштування", action = onCriteriaButtonAction)
        }
    }
}