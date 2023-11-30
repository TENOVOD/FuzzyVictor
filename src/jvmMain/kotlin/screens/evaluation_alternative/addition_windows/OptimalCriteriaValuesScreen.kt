package screens.evaluation_alternative.addition_windows

import GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE
import GLOBAl_CRITERIA_LT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.calculateCriteriaFuzzyNumbers
import navcontroller.NavController
import screens.elements.*

@Composable
fun OptimalCriteriaValuesScreen(
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
                    .fillMaxHeight(0.5f),
                color = Color.Transparent,
            ) {
                Column (
                    modifier = Modifier.padding(10.dp)
                ){

                    Row() {
                        HeaderCell("")
                        HeaderCell("l")
                        HeaderCell("l'")
                        HeaderCell("m")
                        HeaderCell("u'")
                        HeaderCell("u")
                    }
                    GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE.forEach {
                        Row {
                            LeftSideMainCell(it.name)
                            TableCellWithText(it.lValue.toString())
                            TableCellWithText(it.lshtValue.toString())
                            TableCellWithText(it.mValue.toString())
                            TableCellWithText(it.ushtValue.toString())
                            TableCellWithText(it.uValue.toString())
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            calculateNormalizeMatrix()
            Row {
                BasicButton("GO TO PREVIOUS PAGE") {
                    navController.navigate(Screen.EvaluationAlternative.name)
                }
            }
        }

    }
}