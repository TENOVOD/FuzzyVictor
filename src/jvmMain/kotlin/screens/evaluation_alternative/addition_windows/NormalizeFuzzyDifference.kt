package screens.evaluation_alternative.addition_windows

import GLOBAL_COUNT_CRITERIA
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_NORMALIZE_FUZZY_DIFFERENCE
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.calculatePerfectValue
import data.normalizeFuzzyDifference
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText

@Composable
fun NormalizeFuzzyDifferenceScreen(
    navController: NavController
){
    Box(
        modifier = Modifier.fillMaxSize().padding(start = 100.dp)
    ) {
        Column {
            Surface(
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier
                    .padding(15.dp)
                    .width(1750.dp)
                    .height(800.dp)
                    .fillMaxHeight(0.5f)
                    .verticalScroll(rememberScrollState()),
                color = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Row {
                        HeaderCell("Name")
                        for (i in 1..GLOBAL_COUNT_CRITERIA){
                            HeaderCell(GLOBAL_MATRIX_OF_CRITERIA[i-1].name)
                        }
                    }
                    GLOBAL_NORMALIZE_FUZZY_DIFFERENCE.forEach {
                        Row {
                            LeftSideMainCell(it.altName)
                            for (i in 1..GLOBAL_COUNT_CRITERIA){
                                val tempArray = it.table[i]
                                var tempStr ="("
                                tempArray?.forEach {el->
                                    tempStr+="$el,"
                                }
                                tempStr+=")"
                                TableCellWithText(tempStr)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                BasicButton("GO TO PREVIOUS PAGE") {
                    navController.navigate(Screen.EvaluationAlternative.name)
                }
            }
        }

    }
}