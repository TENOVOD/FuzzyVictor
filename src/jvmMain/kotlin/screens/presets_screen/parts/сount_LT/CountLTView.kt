package screens.presets_screen.parts.сount_LT

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import screens.elements.Counter

@Composable
fun CountLT(
    criteriaEvaluation: Int,
    alternativeEvaluation: Int,
    onAddCounterCriteriaValue: (Int) -> Unit,
    onRemoveCounterCriteriaValue: (Int) -> Unit,
    onAddCounterAlternativeValue: (Int) -> Unit,
    onRemoveCounterAlternativeValue: (Int) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(size = 5.dp),
        modifier = Modifier.padding(15.dp),
        color = Color.Transparent
    ) {
        Column(modifier = Modifier.padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Кількість ЛТ", fontSize = 20.sp, modifier = Modifier.padding(end = 5.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Counter("для оцінки критеріїв", criteriaEvaluation,
                onAddCounterValue = onAddCounterCriteriaValue,
                onRemoveCounterValue = onRemoveCounterCriteriaValue
            )
            Spacer(modifier = Modifier.height(5.dp))
            Counter("для оцінки альтернатив", alternativeEvaluation,
                onAddCounterValue = onAddCounterAlternativeValue,
                onRemoveCounterValue = onRemoveCounterAlternativeValue
            )
        }
    }
}