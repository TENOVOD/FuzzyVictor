package screens

import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

@Composable
fun ResultScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                color = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Row {
                        Text("Ранжування за S, R та Q", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        LeftSideMainCell("S")
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING.forEach {
                            TableCellWithText("${it.first} (${it.second})")
                        }
                    }
                    Row {
                        LeftSideMainCell("R")
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING.forEach {
                            TableCellWithText("${it.first} (${it.second})")
                        }
                    }
                    Row {
                        LeftSideMainCell("Q")
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING.forEach {
                            TableCellWithText("${it.first} (${it.second})")
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    //Прийнята перевага
                    val DQ = 1f / (GLOBAL_COUNT_ALTERNATIVE - 1f)
                    val ADV =
                        GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 2].second - GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second
                    val resultCondition = ADV >= DQ
                    Spacer(modifier = Modifier.height(15.dp))
                    Text("Прийнятна перевага", fontSize = 19.sp)
                    Spacer(modifier = Modifier.height(1.dp))
                    Text("Adv ($ADV) >= DQ ($DQ)")
                    Text("Умова: $resultCondition")

                    Spacer(modifier = Modifier.height(30.dp))

                    Text("Прийнятна стабільність у прийнятті рішень", fontSize = 19.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    var resForSecondCondition = true
                    val listForSecondConditional = mutableListOf<Pair<String,Float>>()

                    for (a in 0 until GLOBAL_COUNT_ALTERNATIVE) {
                        val temp =
                            GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].second - GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second
                        if (temp < DQ) {
                            resForSecondCondition = true
                            //Text("${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].second}) - ${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second}) < DQ ($DQ)   Conditional: $resForSecondCondition", color = Color.Green)
                            listForSecondConditional.add(GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].first to GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].second)
                        } else {
                            resForSecondCondition = false
                            //Text("${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[a].second}) - ${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second}) < DQ ($DQ)   Conditional: $resForSecondCondition", color = Color.Red)
                            break
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    if(resultCondition&&resForSecondCondition){

                    }else if(!resultCondition&&resForSecondCondition){
                        Text("Компромістні рішення:")
                        Spacer(modifier = Modifier.height(5.dp))
                        for ( a in listForSecondConditional){
                            Text("${a.first} (${a.second})")
                        }
                    }
                    else if(resultCondition&&!resForSecondCondition){
                        Text("Компромістні рішення:")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text("${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second}) - ${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second}) < DQ ($DQ)   Conditional: $resForSecondCondition")
                        Text("${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 1].second}) - ${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 2].first} (${GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q[GLOBAL_COUNT_ALTERNATIVE - 2].second}) < DQ ($DQ)   Conditional: $resForSecondCondition")
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))

        }

    }

}