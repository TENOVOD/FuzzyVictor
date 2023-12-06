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
import GLOBAl_ALTERNATIVE_LT
import GLOBAl_CRITERIA_LT
import Screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 100.dp)
    ) {
        Column(modifier = Modifier.width(700.dp)) {

            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Linguistic terms for evaluating the importance of criteria")
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    HeaderCell("Full name")
                    HeaderCell("Short name")
                    HeaderCell("")
                    HeaderCell("")
                    HeaderCell("")
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
                val yLabels = remember { listOf("1 ", " ", " ", " ", " ", "0 ") }

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
                            "Update"
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
                Text("Linguistic terms for evaluating the importance of criteria")
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    HeaderCell("Full name")
                    HeaderCell("Short name")
                    HeaderCell("")
                    HeaderCell("")
                    HeaderCell("")
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
                val yLabels = remember { listOf("1 ", " ", " ", " ", " ", "0 ") }



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
                            "Update"
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
        Column {
            BasicButton(
                "DATASET"
            ) {
                GLOBAl_CRITERIA_LT = setFor6LinguisticTerm
                GLOBAl_ALTERNATIVE_LT = setFor6AlternativeTerm

                GLOBAL_COUNT_EV_CRITERIA = 6
                GLOBAL_COUNT_EV_ALTERNATIVE = 6

                GLOBAL_COUNT_CRITERIA = 4
                GLOBAL_COUNT_ALTERNATIVE = 5
                GLOBAL_COUNT_EXPERT = 3

                GLOBAL_MATRIX_OF_CRITERIA = mutableListOf(
                    Criteria(1,"Cri1",BenefitsOrNot.NO),
                    Criteria(2,"Cri2",BenefitsOrNot.YES),
                    Criteria(3,"Cri3",BenefitsOrNot.YES),
                    Criteria(4,"Cri4",BenefitsOrNot.NO),
                )
                GLOBAL_MATRIX_OF_ALTERNATIVES = setFor5Alternatives
                GLOBAL_MATRIX_OF_EXPERTS = setFor3Experts
                GLOBAL_AGGREGATE_SCORE =getEmptyAggregationStore()
                GLOBAL_MATRIX_OF_CRITERIA_EVALUATION = addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA,GLOBAL_COUNT_EXPERT)
                GLOBAL_CRITERIA_FUZZY_NUMBERS = getEmptyCriteriaFuzzyNumbers()
                GLOBAL_MATRIX_OF_CRITERIA_EVALUATION = mutableListOf(
                    CriteriaWithExpertEval(
                        "Cri1",
                        mutableMapOf(
                            1 to "Low",
                            2 to "Medium high",
                            3 to "Medium low",
                        )
                    ),
                    CriteriaWithExpertEval(
                        "Cri2",
                        mutableMapOf(
                            1 to "Low",
                            2 to "Medium",
                            3 to "Medium low",
                        )
                    ),
                    CriteriaWithExpertEval(
                        "Cri3",
                        mutableMapOf(
                            1 to "Medium low",
                            2 to "High",
                            3 to "Medium",
                        )
                    ),
                    CriteriaWithExpertEval(
                        "Cri4",
                        mutableMapOf(
                            1 to "High",
                            2 to "Low",
                            3 to "High",
                        )
                    ),
                )
                updateAggregatedCriteriaWeightMatrix()
                GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                GLOBAL_EXPERTS_EVALUATION_LIST = mutableListOf(
                    ExpertAlternativeEvaluation(
                        1,
                        arrayOf(
                            mutableMapOf(
                                1 to "Medium low",
                                2 to "Low",
                                3 to "Medium high",
                                4 to "Low"
                            ),
                            mutableMapOf(
                                1 to "Low",
                                2 to "High",
                                3 to "Medium low",
                                4 to "Medium high"
                            ),
                            mutableMapOf(
                                1 to "Medium high",
                                2 to "Very low",
                                3 to "Very low",
                                4 to "Medium low"
                            ),
                            mutableMapOf(
                                1 to "Medium low",
                                2 to "Medium",
                                3 to "Very low",
                                4 to "High"
                            ),
                            mutableMapOf(
                                1 to "High",
                                2 to "Medium",
                                3 to "Medium",
                                4 to "Medium high"
                            ),
                        )
                    ),
                    ExpertAlternativeEvaluation(
                        2,
                        arrayOf(
                            mutableMapOf(
                                1 to "High",
                                2 to "Medium high",
                                3 to "Medium high",
                                4 to "Low"
                            ),
                            mutableMapOf(
                                1 to "Very low",
                                2 to "Very low",
                                3 to "Very low",
                                4 to "Very low"
                            ),
                            mutableMapOf(
                                1 to "Very low",
                                2 to "High",
                                3 to "High",
                                4 to "Very low"
                            ),
                            mutableMapOf(
                                1 to "Very low",
                                2 to "High",
                                3 to "Medium",
                                4 to "Medium high"
                            ),
                            mutableMapOf(
                                1 to "High",
                                2 to "High",
                                3 to "Medium high",
                                4 to "High"
                            ),
                        )
                    ),
                    ExpertAlternativeEvaluation(
                        3,
                        arrayOf(
                            mutableMapOf(
                                1 to "High",
                                2 to "Medium high",
                                3 to "Very low",
                                4 to "Medium low"
                            ),
                            mutableMapOf(
                                1 to "Medium low",
                                2 to "High",
                                3 to "Very low",
                                4 to "Very low"
                            ),
                            mutableMapOf(
                                1 to "Medium low",
                                2 to "Medium low",
                                3 to "High",
                                4 to "Medium"
                            ),
                            mutableMapOf(
                                1 to "Medium low",
                                2 to "High",
                                3 to "Medium low",
                                4 to "Medium low"
                            ),
                            mutableMapOf(
                                1 to "High",
                                2 to "High",
                                3 to "High",
                                4 to "Very low"
                            ),
                        )
                    ),
                )
                GLOBAL_AGGREGATE_SCORE =getEmptyAggregationStore()
                normalizeAlternativeLT()
                getAggregateStore()
                updateAggAlternativeWeightMatrix()
                calculatePerfectValue()
                normalizeFuzzyDifference()
                calculateS()
                calculateR()
                calculateQ()
                calculateDefuzzification()
            }

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
