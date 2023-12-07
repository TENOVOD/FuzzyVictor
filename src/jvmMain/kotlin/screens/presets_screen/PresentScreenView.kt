package screens.presets_screen

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EV_ALTERNATIVE
import GLOBAL_COUNT_EV_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_CRITERIA_FUZZY_NUMBERS
import GLOBAL_EXPERTS_EVALUATION_LIST
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import GLOBAL_MATRIX_OF_EXPERTS
import GLOBAL_V
import GLOBAl_ALTERNATIVE_LT
import GLOBAl_CRITERIA_LT
import Screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.*
import models.*
import navcontroller.NavController
import screens.elements.*
import screens.evaluation_criteria.normalizeAlternativeLT
import screens.presets_screen.alternative_count.AlternativeCountView
import screens.presets_screen.alternatives_names.updateTableByAlternative

import screens.presets_screen.criteria_count.CriteriaCountView
import screens.presets_screen.expert_count.updateTableByExpertsCount

import screens.presets_screen.experts_count.ExpertsCountView
import screens.presets_screen.experts_count.VCountView
import screens.presets_screen.linguistic_terms_for_evaluating_the_importance_of_criteria.updateDataAlternativeMatrix
import screens.presets_screen.linguistic_terms_for_evaluating_the_importance_of_criteria.updateDataMatrix

import screens.presets_screen.parts.сount_LT.CountLT
import screens.presets_screen.settings_of_alternatives.updateTableByCriteria


@Composable
fun PresentScreenView(
    navController: NavController
) {
    var rememberCriteriaEvaluation by remember { mutableStateOf(GLOBAL_COUNT_EV_CRITERIA) }
    var rememberAlternativeEvaluation by remember { mutableStateOf(GLOBAL_COUNT_EV_ALTERNATIVE) }
    val criteriaDataPoints = remember { mutableStateListOf(listOf(0f, 0f, 0f)) }
    val alternativeDataPoints = remember { mutableStateListOf(listOf(0f, 0f, 0f)) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Column(modifier = Modifier.width(950.dp)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Лінгвістичні терми для оцінки важливості критеріїв",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        HeaderCell("Повна назва")
                        HeaderCell("Коротка назва")
                        HeaderCell("Перша точка")
                        HeaderCell("Друга точка")
                        HeaderCell("Третя точка")
                    }
                    when (rememberCriteriaEvaluation) {
                        3 -> {
                            updateDataMatrix(GLOBAl_CRITERIA_LT)
                        }

                        4 -> {
                            updateDataMatrix(GLOBAl_CRITERIA_LT)
                        }

                        5 -> {
                            updateDataMatrix(GLOBAl_CRITERIA_LT)
                        }

                        6 -> {
                            updateDataMatrix(GLOBAl_CRITERIA_LT)
                        }

                        7 -> {
                            updateDataMatrix(GLOBAl_CRITERIA_LT)
                        }
                    }

                    var preparationForDataPoint by remember { mutableStateOf(GLOBAl_CRITERIA_LT) }

                    criteriaDataPoints.clear()
                    var maxX by remember { mutableStateOf(0) }
                    var xLabels = remember { listOf("0") }
                    val yLabels = remember { listOf("1 ", "0.2 ", "0.4 ", "0.6 ", "0.8 ", "0 ") }

                    preparationForDataPoint.forEach {
                        if (maxX < it.thirdLimit.toFloat()) {
                            maxX = it.thirdLimit.toInt()
                        }
                        criteriaDataPoints.add(
                            listOf(
                                it.firstLimit.toFloat(),
                                it.secondLimit.toFloat(),
                                it.thirdLimit.toFloat()
                            )
                        )
                    }
                    xLabels = newXLabels(maxX)

                    //Charts
                    Row {
                        Column(
                            modifier = Modifier
                                .width(400.dp)
                                .padding(19.dp)
                        ) {
                            LineChartWithLabels(
                                dataPoints = criteriaDataPoints,
                                xLabels = xLabels,
                                yLabels = yLabels,
                                maxX = maxX.toFloat(),
                                modifier = Modifier
                            )
                            BasicButton(
                                "Оновити графіки"
                            ) {
                                preparationForDataPoint = GLOBAl_CRITERIA_LT
                                criteriaDataPoints.clear()
                                maxX = 0
                                preparationForDataPoint.forEach {
                                    if (maxX < it.thirdLimit.toFloat()) {
                                        maxX = it.thirdLimit.toInt()
                                    }
                                    criteriaDataPoints.add(
                                        listOf(
                                            it.firstLimit.toFloat(),
                                            it.secondLimit.toFloat(),
                                            it.thirdLimit.toFloat()
                                        )
                                    )
                                }
                                xLabels = newXLabels(maxX)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .width(400.dp)
                                .padding(19.dp)
                        ) {
                            LineChartWithLabels(
                                dataPoints = criteriaDataPoints,
                                xLabels = listOf("0", "0.2", "0.4", "0.6", "0.8", "1"),
                                yLabels = yLabels,
                                maxX = maxX.toFloat(),
                                modifier = Modifier
                            )
                        }
                    }

                    /*
                    for (row in rememberCriteriaDataMatrix) {
                        setMatrixOfLTForEvaluatingTheImportanceOfCriteria(
                            ltCell=row,
                            changeFullName = {row.fullName=it},
                            changeShortName = {row.onNewShortName},
                            changeFirstLimit = {row.onNewFirstLimit},
                            changeSecondLimit = {row.onNewSecondLimit},
                            changeThirdLimit = {row.onNewThirdLimit}
                        )
                    }*/

                }
            }
            Column(modifier = Modifier.width(700.dp)) {

                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Лінгвістичні терми для оцінки альтернатив", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        HeaderCell("Повна назва")
                        HeaderCell("Коротка назва")
                        HeaderCell("Перша точка")
                        HeaderCell("Друга точка")
                        HeaderCell("Третя точка")
                    }

                    when (rememberAlternativeEvaluation) {
                        3 -> {
                            updateDataAlternativeMatrix(GLOBAl_ALTERNATIVE_LT)
                        }

                        4 -> {
                            updateDataAlternativeMatrix(GLOBAl_ALTERNATIVE_LT)
                        }

                        5 -> {
                            updateDataAlternativeMatrix(GLOBAl_ALTERNATIVE_LT)
                        }

                        6 -> {
                            updateDataAlternativeMatrix(GLOBAl_ALTERNATIVE_LT)
                        }

                        7 -> {
                            updateDataAlternativeMatrix(GLOBAl_ALTERNATIVE_LT)
                        }
                    }

                    var preparationForDataPoint by remember { mutableStateOf(GLOBAl_ALTERNATIVE_LT) }
                    alternativeDataPoints.clear()
                    var maxX by remember { mutableStateOf(0) }
                    var xLabels = remember { listOf("0") }
                    val yLabels = remember { listOf("1 ", "0.2 ", "0.4 ", "0.6 ", "0.8 ", "0 ") }



                    preparationForDataPoint.forEach {
                        if (maxX < it.thirdLimit.toFloat()) {
                            maxX = it.thirdLimit.toInt()
                        }
                        alternativeDataPoints.add(
                            listOf(
                                it.firstLimit.toFloat(),
                                it.secondLimit.toFloat(),
                                it.thirdLimit.toFloat()
                            )
                        )
                    }
                    xLabels = newXLabels(maxX)

                    //Charts
                    Row {
                        Column(
                            modifier = Modifier
                                .width(400.dp)
                                .padding(19.dp)
                        ) {
                            LineChartWithLabels(
                                dataPoints = alternativeDataPoints,
                                xLabels = xLabels,
                                yLabels = yLabels,
                                maxX = maxX.toFloat(),
                                modifier = Modifier
                            )
                            BasicButton(
                                "Оновити графіки"
                            ) {
                                preparationForDataPoint = GLOBAl_ALTERNATIVE_LT
                                alternativeDataPoints.clear()
                                maxX = 0
                                preparationForDataPoint.forEach {
                                    if (maxX < it.thirdLimit.toFloat()) {
                                        maxX = it.thirdLimit.toInt()
                                    }
                                    alternativeDataPoints.add(
                                        listOf(
                                            it.firstLimit.toFloat(),
                                            it.secondLimit.toFloat(),
                                            it.thirdLimit.toFloat()
                                        )
                                    )
                                }
                                xLabels = newXLabels(maxX)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .width(400.dp)
                                .padding(19.dp)
                        ) {
                            LineChartWithLabels(
                                dataPoints = alternativeDataPoints,
                                xLabels = listOf("0", "0.2", "0.4", "0.6", "0.8", "1"),
                                yLabels = yLabels,
                                maxX = maxX.toFloat(),
                                modifier = Modifier
                            )
                        }
                    }
                }
            }

        }
        Row(horizontalArrangement = Arrangement.Center) {

            //Counters LT
            CountLT(
                rememberCriteriaEvaluation,
                rememberAlternativeEvaluation,
                onAddCounterCriteriaValue = {
                    if (rememberCriteriaEvaluation < 7) {
                        rememberCriteriaEvaluation++
                        GLOBAL_COUNT_EV_CRITERIA = rememberCriteriaEvaluation
                        updateMatrixByCriteriaCount(rememberCriteriaEvaluation)
                    }
                },
                onRemoveCounterCriteriaValue = {
                    if (rememberCriteriaEvaluation > 3) {
                        rememberCriteriaEvaluation--
                        GLOBAL_COUNT_EV_CRITERIA = rememberCriteriaEvaluation
                        updateMatrixByCriteriaCount(rememberCriteriaEvaluation)
                    }
                },
                onAddCounterAlternativeValue = {
                    if (rememberAlternativeEvaluation < 7) {
                        rememberAlternativeEvaluation++
                        GLOBAL_COUNT_EV_ALTERNATIVE = rememberAlternativeEvaluation
                        updateMatrixByAlternativeCount(rememberAlternativeEvaluation)
                    }
                },
                onRemoveCounterAlternativeValue = {
                    if (rememberAlternativeEvaluation > 3) {
                        rememberAlternativeEvaluation--
                        GLOBAL_COUNT_EV_ALTERNATIVE = rememberAlternativeEvaluation
                        updateMatrixByAlternativeCount(rememberAlternativeEvaluation)
                    }
                }
            )

            //Criteria counter +-
            var rememberCriteriaCount by remember { mutableStateOf(GLOBAL_COUNT_CRITERIA) }
            CriteriaCountView(
                rememberCriteriaCount,
                onAddCounterCriteriaValue = {
                    if (rememberCriteriaCount < 10) {
                        rememberCriteriaCount++
                        GLOBAL_COUNT_CRITERIA = rememberCriteriaCount
                        updateTableByCriteria(rememberCriteriaCount)
                        GLOBAL_MATRIX_OF_CRITERIA_EVALUATION =
                            addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA, GLOBAL_COUNT_EXPERT)
                        GLOBAL_CRITERIA_FUZZY_NUMBERS = getEmptyCriteriaFuzzyNumbers()
                        GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                        GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                    }
                },
                onRemoveCounterCriteriaValue = {
                    if (rememberCriteriaCount > 2) {
                        rememberCriteriaCount--
                        GLOBAL_COUNT_CRITERIA = rememberCriteriaCount
                        updateTableByCriteria(rememberCriteriaCount)
                        GLOBAL_MATRIX_OF_CRITERIA_EVALUATION =
                            addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA, GLOBAL_COUNT_EXPERT)
                        GLOBAL_CRITERIA_FUZZY_NUMBERS = getEmptyCriteriaFuzzyNumbers()
                        GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                        GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                    }
                },
                onCriteriaButtonAction = {
                    navController.navigate(Screen.CriteriaSettings.name)
                }
            )

            //Alternative counter +-
            var rememberAlternativeCount by remember { mutableStateOf(GLOBAL_COUNT_ALTERNATIVE) }
            AlternativeCountView(
                rememberAlternativeCount,
                onAddCounterAlternativeValue = {
                    if (rememberAlternativeCount < 10) {
                        rememberAlternativeCount++
                        GLOBAL_COUNT_ALTERNATIVE = rememberAlternativeCount
                        updateTableByAlternative(rememberAlternativeCount)
                        GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                        GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                    }
                },
                onRemoveCounterAlternativeValue = {
                    if (rememberAlternativeCount > 2) {
                        rememberAlternativeCount--
                        GLOBAL_COUNT_ALTERNATIVE = rememberAlternativeCount
                        updateTableByAlternative(rememberAlternativeCount)
                        GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                        GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                    }
                },
                onAlternativeButtonAction = {
                    navController.navigate(Screen.AlternativesName.name)
                }
            )

            // Expert counter +-
            var rememberExpertsCount by remember { mutableStateOf(GLOBAL_COUNT_EXPERT) }
            ExpertsCountView(
                rememberExpertsCount,
                onAddCounterExpertValue = {
                    if (rememberExpertsCount < 10) {
                        rememberExpertsCount++
                        GLOBAL_COUNT_EXPERT = rememberExpertsCount
                        updateTableByExpertsCount(rememberExpertsCount)
                        GLOBAL_MATRIX_OF_CRITERIA_EVALUATION =
                            addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA, GLOBAL_COUNT_EXPERT)
                        GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                        GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                    }
                },
                onRemoveCounterExpertValue = {
                    if (rememberExpertsCount > 1) {
                        rememberExpertsCount--
                        GLOBAL_COUNT_EXPERT = rememberExpertsCount
                        updateTableByExpertsCount(rememberExpertsCount)
                        GLOBAL_MATRIX_OF_CRITERIA_EVALUATION =
                            addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA, GLOBAL_COUNT_EXPERT)
                        GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                        GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                    }
                },
                onExpertsButton = {
                    navController.navigate(Screen.ExpertsName.name)
                }
            )

            var rememberV by remember { mutableStateOf(GLOBAL_V) }
            VCountView(
                rememberV,
                onAddCounterExpertValue = {
                    if (rememberV < 1f) {
                        rememberV += 0.1f
                        GLOBAL_V = rememberV

                    }
                },
                onRemoveCounterExpertValue = {
                    if (rememberV > 0) {
                        rememberV -= 0.1f
                        GLOBAL_V = rememberV
                    }
                }
            )

        }
    }


}

fun updateMatrixByAlternativeCount(
    count: Int
) {
    when (count) {
        3 -> {
            GLOBAl_ALTERNATIVE_LT = setFor3AlternativeTerm
        }

        4 -> {
            GLOBAl_ALTERNATIVE_LT = setFor4AlternativeTerm
        }

        5 -> {
            GLOBAl_ALTERNATIVE_LT = setFor5AlternativeTerm
        }

        6 -> {
            GLOBAl_ALTERNATIVE_LT = setFor6AlternativeTerm
        }

        7 -> {
            GLOBAl_ALTERNATIVE_LT = setFor7AlternativeTerm
        }
    }
}

fun updateMatrixByCriteriaCount(
    count: Int
) {
    when (count) {
        3 -> {
            GLOBAl_CRITERIA_LT = setFor3LinguisticTerm

        }

        4 -> {
            GLOBAl_CRITERIA_LT = setFor4LinguisticTerm
        }

        5 -> {
            GLOBAl_CRITERIA_LT = setFor5LinguisticTerm
        }

        6 -> {
            GLOBAl_CRITERIA_LT = setFor6LinguisticTerm
        }

        7 -> {
            GLOBAl_CRITERIA_LT = setFor7LinguisticTerm
        }
    }
}

fun updateDataInList(
    currentList: MutableList<LinguisticTermCell>,
    oldFullName: String,
): Int {
    var index = 0
    for (i in currentList.indices) {
        if (currentList[i].fullName == oldFullName) {
            index = i
        }
    }
    return index
}
