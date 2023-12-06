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
        modifier = Modifier.fillMaxSize().padding(start = 100.dp)
    ) {
        Row {
            Column {
                Surface(
                    shape = RoundedCornerShape(size = 5.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .padding(15.dp)
                        .width(1350.dp)
                        .height(800.dp)
                        .fillMaxHeight(0.5f),
                    color = Color.Transparent,
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState()),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ){
                        var indexExperts = 0

                        GLOBAL_EXPERTS_EVALUATION_LIST.forEach {ev->
                            Row {
                                HeaderCell(GLOBAL_MATRIX_OF_EXPERTS[indexExperts].name)
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
                            Spacer(modifier = Modifier.height(50.dp))
                        }

                        println("@@@@@@")
                        GLOBAL_EXPERTS_EVALUATION_LIST.forEach {
                            println("; ; $it")
                            it.table.forEach {el->

                            }
                        }
                        println("2222222222")

                    }


                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    BasicButton("GO TO MAIN PAGE") {
                        //navController.navigate(Screen.FuzzyTriangularNumbers.name)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width(400.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(size = 5.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .padding(15.dp)
                        .width(1350.dp)
                        .height(800.dp)
                        .fillMaxHeight(0.5f),
                    color = Color.Transparent,
                ){
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        BasicButton("Aggregate score") {
                            getAggregateStore()
                            navController.navigate(Screen.AggregateScoreScreen.name)
                            //navController.navigate(Screen.FuzzyTriangularNumbers.name)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BasicButton("Estimates in the form of fuzzy triangular numbers") {
                            getAggregateStore()
                            navController.navigate(Screen.EstimatesInTheFormOfFuzzyTriangularNumbersScreen.name)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BasicButton("Estimates in the form of fuzzy numbers") {
                            navController.navigate(Screen.EstimatesInTheFormOfFuzzyNumberScreen.name)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BasicButton("BenefitsOrMaximization") {
                            navController.navigate(Screen.BenefitsOrMaximizationScreen.name)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BasicButton("Normalized matrix") {
                            navController.navigate(Screen.NormalizeFuzzyDifferenceScreen.name)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BasicButton("Normalized weighted matrix") {

                            navController.navigate(Screen.SRQScreen.name)
                        }
                    }

                }

            }

        }
    }
}