package screens.evaluation_alternative.addition_windows

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS
import GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT
import GLOBAL_NORMALIZE_OF_CRITERIA_LT
import GLOBAl_ALTERNATIVE_LT
import GLOBAl_CRITERIA_LT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.AlternativeAndCriteriaFuzzyNumbers
import models.LinguisticTermCell
import models.TypeMinMax
import models.calculateCriteriaFuzzyNumbers
import navcontroller.NavController
import screens.elements.*
import kotlin.math.pow

@Composable
fun EstimatesInTheFormOfFuzzyNumber(
    navController: NavController
) {
    GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS.clear()
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
                    GLOBAL_AGGREGATE_SCORE.forEach {
                        Row() {
                            HeaderCell(it.altName)
                            HeaderCell("l")
                            HeaderCell("l'")
                            HeaderCell("m")
                            HeaderCell("u'")
                            HeaderCell("u")
                        }
                        for (c in 1..GLOBAL_COUNT_CRITERIA) {
                            val arr = it.table[c]
                            val arrOfMinValues = Array<Float>(GLOBAL_COUNT_EXPERT) { 0f }
                            val arrOfMiddleValues = Array(GLOBAL_COUNT_EXPERT) { 0f }
                            val arrOfMaxValues = Array(GLOBAL_COUNT_EXPERT) { 0f }

                            for (i in arr?.indices!!) {
                                val limitsArray = getLimitsByShortName(arr[i])

                                arrOfMinValues[i] = limitsArray.min()
                                arrOfMaxValues[i] = limitsArray.max()
                                arrOfMiddleValues[i] = limitsArray[1]
                            }
                            var multMinValue = 0f
                            var multMiddleValue = 0f
                            var multMaxValue = 0f

                            for (i in arrOfMinValues.indices) {
                                if (i == 0) {
                                    multMinValue = arrOfMinValues[i]
                                    multMiddleValue = arrOfMiddleValues[i]
                                    multMaxValue = arrOfMaxValues[i]
                                } else {
                                    multMinValue *= arrOfMinValues[i]
                                    multMiddleValue *= arrOfMiddleValues[i]
                                    multMaxValue *= arrOfMaxValues[i]
                                }
                            }
                            val lValue = arrOfMinValues.min()
                            val lshtValue = multMinValue.pow((1 / 3f))
                            val mValue = multMiddleValue.pow((1 / 3f))
                            val ushtValue = multMaxValue.pow((1 / 3f))
                            val uValue = arrOfMaxValues.max()
                            println("Print alt name: ${it.altName}")
                            GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS.add(it.altName to  AlternativeAndCriteriaFuzzyNumbers(
                                GLOBAL_MATRIX_OF_CRITERIA[c-1].name,
                                lValue,
                                lshtValue,
                                mValue,
                                ushtValue,
                                uValue
                            ))
                            Row {
                                TableCellWithText(GLOBAL_MATRIX_OF_CRITERIA[c-1].name)
                                TableCellWithText(lValue.toString())
                                TableCellWithText(lshtValue.toString())
                                TableCellWithText(mValue.toString())
                                TableCellWithText(ushtValue.toString())
                                TableCellWithText(uValue.toString())
                            }

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE.clear()
                    GLOBAL_MATRIX_OF_CRITERIA.forEach {
                        GLOBAL_ALTERNATIVE_FUZZY_NUMBERS_BY_CRITERIA_TYPE.add(chooseMinOrMaxCriteria(criteriaName = it.name, type = it.type))
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

fun getLimitsByShortName(shortName: String): Array<Float> {
    val result = Array(3) { 0f }
    var temp = LinguisticTermCell("", "", "", "", "")
    GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT.forEach {
        if (it.shortName == shortName) {
            temp = it
        }
    }
    result[0] = temp.firstLimit.toFloat()
    result[1] = temp.secondLimit.toFloat()
    result[2] = temp.thirdLimit.toFloat()
    return result
}

fun chooseMinOrMaxCriteria(
    criteriaName:String,
    type: TypeMinMax
): AlternativeAndCriteriaFuzzyNumbers {
    val tempList = mutableListOf<AlternativeAndCriteriaFuzzyNumbers>()
    GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS.forEach {
        if(criteriaName==it.second.name){
            tempList.add(it.second)
        }
    }
    var lValue = tempList[0].lValue
    var lshtValue = tempList[0].lshtValue
    var mValue = tempList[0].mValue
    var ushtValue = tempList[0].ushtValue
    var uValue = tempList[0].uValue
    tempList.forEach {
        if(type==TypeMinMax.MIN){
            if(it.lValue<=lValue){
                lValue=it.lValue
            }
            if(it.lshtValue<=lshtValue){
                lshtValue=it.lshtValue
            }
            if(it.mValue<=mValue){
                mValue=it.mValue
            }
            if(it.ushtValue<=ushtValue){
                ushtValue=it.ushtValue
            }
            if(it.uValue<=uValue){
                uValue=it.uValue
            }
        }
        if(type==TypeMinMax.MAX){
            if(it.lValue>=lValue){
                lValue=it.lValue
            }
            if(it.lshtValue>=lshtValue){
                lshtValue=it.lshtValue
            }
            if(it.mValue>=mValue){
                mValue=it.mValue
            }
            if(it.ushtValue>=ushtValue){
                ushtValue=it.ushtValue
            }
            if(it.uValue>=uValue){
                uValue=it.uValue
            }
        }
    }
    return AlternativeAndCriteriaFuzzyNumbers(criteriaName,lValue,lshtValue,mValue,ushtValue,uValue)


}