package screens.evaluation_alternative

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_EXPERTS_EVALUATION_LIST
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_MATRIX_OF_EXPERTS
import GLOBAl_ALTERNATIVE_LT

import Screen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.getAggregateStore
import navcontroller.NavController
import screens.elements.*
import screens.evaluation_criteria.normalizeAlternativeLT


@Composable
fun EvaluationAlternativeScreen(
    navController: NavController
) {
    normalizeAlternativeLT()
    Box(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Row {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                    ,
                    color = Color.Transparent,
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {

                        var indexExperts = 0
                        GLOBAL_EXPERTS_EVALUATION_LIST.forEach {ev->
                            Text("Експерт: ${GLOBAL_MATRIX_OF_EXPERTS[indexExperts].name}")
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {
                                HeaderCell("")
                                for (i in GLOBAL_MATRIX_OF_CRITERIA) {
                                    HeaderCell(i.name)
                                }
                            }
                            val listOfAlternativeLt = mutableListOf<String>()
                            GLOBAl_ALTERNATIVE_LT.forEach {
                                listOfAlternativeLt.add(it.fullName)
                            }
                            var indexA = 0;
                            ev.table.forEach {
                                Row {
                                    LeftSideMainCell(GLOBAL_MATRIX_OF_ALTERNATIVES[indexA].name)
                                    for (c in 1..GLOBAL_COUNT_CRITERIA) {
                                        DropdownAlternativeEvaluation(
                                            ev.table[indexA][c],
                                            indexA,
                                            c,
                                            ev.expertId,
                                            listOfAlternativeLt
                                        )

                                    }
                                }
                                indexA++
                            }

                            indexExperts++
                        }


                    }


                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {

                    BasicButton("У трикутній формі") {
                        getAggregateStore()
                        navController.navigate(Screen.EstimatesInTheFormOfFuzzyTriangularNumbersScreen.name)
                    }
                    BasicButton("Агреговані оцінки альтернатив") {
                        navController.navigate(Screen.EstimatesInTheFormOfFuzzyNumberScreen.name)
                    }
                    BasicButton("Ідеальні та найгірші значення критеріїв") {
                        navController.navigate(Screen.BenefitsOrMaximizationScreen.name)
                    }

                }
                Row{


                    BasicButton("Нормована нечітка різниця") {
                        navController.navigate(Screen.NormalizeFuzzyDifferenceScreen.name)
                    }

                    BasicButton("Звичайні SRQ та дефазифіковані") {

                        navController.navigate(Screen.SRQScreen.name)
                    }
                }
            }

        }
    }
}