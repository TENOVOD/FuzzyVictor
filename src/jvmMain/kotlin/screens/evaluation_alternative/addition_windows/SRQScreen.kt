package screens.evaluation_alternative.addition_windows

import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_R
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_S
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_Q
import GLOBAL_MATRIX_OF_R
import GLOBAL_MATRIX_OF_S
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.calculateQ
import data.calculateR
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText

@Composable
fun SRQScreen(
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
                    .fillMaxHeight(0.5f)
                    .verticalScroll(rememberScrollState()),
                color = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row {
                        HeaderCell("")
                        GLOBAL_MATRIX_OF_ALTERNATIVES.forEach {
                            HeaderCell(it.name)
                        }
                    }
                    Row {
                        LeftSideMainCell("S")
                        for (a in 1..GLOBAL_COUNT_ALTERNATIVE){
                            val tempArray = GLOBAL_MATRIX_OF_S.table[a]
                            var result="("
                            tempArray?.forEach {
                                result+="$it, "
                            }
                            result+=")"
                            TableCellWithText(result)
                        }
                    }
                    Row {
                        LeftSideMainCell("R")
                        for (a in 1..GLOBAL_COUNT_ALTERNATIVE){
                            val tempArray = GLOBAL_MATRIX_OF_R.table[a]
                            var result="("
                            tempArray?.forEach {
                                result+="$it, "
                            }
                            result+=")"
                            TableCellWithText(result)
                        }
                    }
                    Row {
                        LeftSideMainCell("R")
                        for (a in 1..GLOBAL_COUNT_ALTERNATIVE){
                            val tempArray = GLOBAL_MATRIX_OF_Q.table[a]
                            var result="("
                            tempArray?.forEach {
                                result+="$it, "
                            }
                            result+=")"
                            TableCellWithText(result)
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row{
                        HeaderCell("Defuzzified values")
                        GLOBAL_MATRIX_OF_ALTERNATIVES.forEach {
                            HeaderCell(it.name)
                        }
                    }
                    Row {
                        LeftSideMainCell("Defuzzified sum(S)")
                        for (i in 1..GLOBAL_COUNT_ALTERNATIVE){
                            TableCellWithText(GLOBAL_DEFUZZIFICATION_MATRIX_OF_S[i-1].second.toString())
                        }
                    }
                    Row {
                        LeftSideMainCell("Defuzzified max(R)")
                        for (i in 1..GLOBAL_COUNT_ALTERNATIVE){
                            TableCellWithText(GLOBAL_DEFUZZIFICATION_MATRIX_OF_R[i-1].second.toString())
                        }
                    }
                    Row {
                        LeftSideMainCell("Defuzzified score(Q)")
                        for (i in 1..GLOBAL_COUNT_ALTERNATIVE){
                            TableCellWithText(GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[i-1].second.toString())
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row {
                        Text("Ranked alternatives")
                    }
                    Row {
                        LeftSideMainCell("Ranked sum(S)")
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING.forEach {
                            TableCellWithText("${it.first} (${it.second})")
                        }
                    }
                    Row {
                        LeftSideMainCell("Ranked max(R)")
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING.forEach {
                            TableCellWithText("${it.first} (${it.second}) ->")
                        }
                    }
                    Row {
                        LeftSideMainCell("Ranked score(Q)")
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING.forEach {
                            TableCellWithText("${it.first} (${it.second}) ->")
                        }
                    }
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