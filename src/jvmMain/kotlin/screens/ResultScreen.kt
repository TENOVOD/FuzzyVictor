package screens

import GLOBAL_RESULT
import GLOBAL_S_VAlUES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText
import screens.evaluation_alternative.addition_windows.getLimitsInStringByShortName

@Composable
fun ResultScreen(
    navController: NavController
) {
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
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text("RESULT", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                    Row {
                        HeaderCell("Name")
                        HeaderCell("l")
                        HeaderCell("l'")
                        HeaderCell("m")
                        HeaderCell("u'")
                        HeaderCell("u")
                    }
                    GLOBAL_RESULT.forEach {
                        Row{
                            LeftSideMainCell(it.first)
                            TableCellWithText(it.second.lValue.toString())
                            TableCellWithText(it.second.lshtValue.toString())
                            TableCellWithText(it.second.mValue.toString())
                            TableCellWithText(it.second.ushtValue.toString())
                            TableCellWithText(it.second.uValue.toString())
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row{
                        HeaderCell("Name")
                        HeaderCell("Defuzzification")
                        HeaderCell("Degree of Utility")
                    }
                    GLOBAL_S_VAlUES.forEach {
                        Row{
                            LeftSideMainCell(it.first)
                            TableCellWithText(it.second[0].toString())
                            TableCellWithText(it.second[1].toString())
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}