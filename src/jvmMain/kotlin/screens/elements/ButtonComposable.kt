package screens.elements


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BasicButton(
    text: String,
    modifier: Modifier = Modifier
        .padding(12.dp)
        .padding(1.dp),
    enabled: Boolean = true,
    action:() -> Unit)
{
    Button(
        onClick = action,
        modifier = modifier,
        border = BorderStroke(1.dp,Color.Black),
        enabled= enabled,
        colors =
            ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black,
            )
    ) {
        Text(text = text,fontSize = 16.sp,

        )
    }
}