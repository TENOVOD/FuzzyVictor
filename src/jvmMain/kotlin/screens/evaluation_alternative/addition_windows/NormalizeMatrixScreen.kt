package screens.evaluation_alternative.addition_windows

import GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS
import GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE
import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_NORMALIZED_ALTERNATIVE_MATRIX
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.AlternativeAndCriteriaFuzzyNumbers
import models.TypeMinMax
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText

@Composable
fun NormalizedMatrixScreen(
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
                    for (i in 0 until GLOBAL_COUNT_CRITERIA) {
                        Row() {
                            HeaderCell(GLOBAL_MATRIX_OF_CRITERIA[i].name)
                            HeaderCell("l")
                            HeaderCell("l'")
                            HeaderCell("m")
                            HeaderCell("u'")
                            HeaderCell("u")
                        }
                        var index = 0
                        GLOBAL_NORMALIZED_ALTERNATIVE_MATRIX.forEach {
                            if (it.first == GLOBAL_MATRIX_OF_CRITERIA[i].name) {
                                Row {
                                    if (index == 0) {
                                        LeftSideMainCell("Optimal alternative")
                                        TableCellWithText(it.second.lValue.toString())
                                        TableCellWithText(it.second.lshtValue.toString())
                                        TableCellWithText(it.second.mValue.toString())
                                        TableCellWithText(it.second.ushtValue.toString())
                                        TableCellWithText(it.second.uValue.toString())
                                        index++
                                    } else {
                                        var name = "$index"
                                        if (index < GLOBAL_COUNT_ALTERNATIVE + 1) {
                                            name = GLOBAL_MATRIX_OF_ALTERNATIVES[index - 1].name
                                        }
                                        LeftSideMainCell(name)
                                        TableCellWithText(it.second.lValue.toString())
                                        TableCellWithText(it.second.lshtValue.toString())
                                        TableCellWithText(it.second.mValue.toString())
                                        TableCellWithText(it.second.ushtValue.toString())
                                        TableCellWithText(it.second.uValue.toString())
                                        index++
                                    }
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            calculateNormalizeMatrix()
            Row {
                BasicButton("GO TO PREVIOUS PAGE") {
                    navController.navigate(Screen.EvaluationAlternative.name)
                }
            }
        }

    }
}


fun calculateNormalizeMatrix() {
    GLOBAL_NORMALIZED_ALTERNATIVE_MATRIX.clear()
    val result = mutableListOf<Pair<String, AlternativeAndCriteriaFuzzyNumbers>>()
    for (i in 0 until GLOBAL_COUNT_CRITERIA) {
        val key = GLOBAL_MATRIX_OF_CRITERIA[i].name
        val optimalAlternative = calculateNormalizeValue(key, GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE[i])
        GLOBAL_NORMALIZED_ALTERNATIVE_MATRIX.add(key to optimalAlternative)
        for (a in 0 until GLOBAL_COUNT_ALTERNATIVE) {
            GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS.forEach {
                if (it.first == GLOBAL_MATRIX_OF_ALTERNATIVES[a].name) {
                    if (it.second.name == key) {
                        GLOBAL_NORMALIZED_ALTERNATIVE_MATRIX.add(key to calculateNormalizeValue(key, it.second))
                    }
                }
            }
        }

    }
}

fun calculateNormalizeValue(
    key: String,
    fuzzyNumbers: AlternativeAndCriteriaFuzzyNumbers
): AlternativeAndCriteriaFuzzyNumbers {

    println("START ** CALCULATE NORMALIZE VALUE ** START")
    println("fuzzyNumbers: $fuzzyNumbers  key: $key")
    var countMaxCriteria = 0
    var countMinCriteria = 0
    var currentType = TypeMinMax.MAX
    val optimalAlternative: AlternativeAndCriteriaFuzzyNumbers
    for (i in GLOBAL_MATRIX_OF_CRITERIA) {
        if (i.type == TypeMinMax.MAX) {
            countMaxCriteria++
        }
        if (i.type == TypeMinMax.MIN) {
            countMinCriteria++
        }
        if (i.name == key) {
            currentType = i.type
        }
    }
    if (currentType == TypeMinMax.MAX) {

        val normalizeLValue = fuzzyNumbers.lValue / countMaxCriteria
        val normalizeLshValue = fuzzyNumbers.lshtValue / countMaxCriteria
        val normalizeMValue = fuzzyNumbers.mValue / countMaxCriteria
        val normalizeUshtValue = fuzzyNumbers.ushtValue / countMaxCriteria
        val normalizeUValue = fuzzyNumbers.uValue / countMaxCriteria
        optimalAlternative = AlternativeAndCriteriaFuzzyNumbers(
            fuzzyNumbers.name,
            normalizeLValue,
            normalizeLshValue,
            normalizeMValue,
            normalizeUshtValue,
            normalizeUValue
        )
    } else {
        val normalizeLValue = (1 / fuzzyNumbers.lValue) / countMinCriteria
        val normalizeLshValue = (1 / fuzzyNumbers.lshtValue) / countMinCriteria
        val normalizeMValue = (1 / fuzzyNumbers.mValue) / countMinCriteria
        val normalizeUshtValue = (1 / fuzzyNumbers.ushtValue) / countMinCriteria
        val normalizeUValue = (1 / fuzzyNumbers.uValue) / countMinCriteria
        optimalAlternative = AlternativeAndCriteriaFuzzyNumbers(
            fuzzyNumbers.name,
            normalizeLValue,
            normalizeLshValue,
            normalizeMValue,
            normalizeUshtValue,
            normalizeUValue
        )
    }

    return optimalAlternative
}