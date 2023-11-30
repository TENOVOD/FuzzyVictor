package models

import GLOBAL_COUNT_EXPERT
import GLOBAL_CRITERIA_FUZZY_NUMBERS
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import screens.evaluation_criteria.criterion_evaluation_in_the_form_of_fuzzy_triangular_numbers.findLimitsByName


import kotlin.math.pow

data class AlternativeAndCriteriaFuzzyNumbers(
    var name: String,
    var lValue: Float,
    var lshtValue: Float,
    var mValue: Float,
    var ushtValue: Float,
    var uValue: Float
)

fun getEmptyCriteriaFuzzyNumbers(): MutableList<AlternativeAndCriteriaFuzzyNumbers> {
    val result = mutableListOf<AlternativeAndCriteriaFuzzyNumbers>()
    GLOBAL_MATRIX_OF_CRITERIA.forEach {
        result.add(
            AlternativeAndCriteriaFuzzyNumbers("", 0f, 0f, 0f, 0f, 0f)
        )
    }
    return result
}

fun calculateCriteriaFuzzyNumbers() {
    var index=0
    GLOBAL_MATRIX_OF_CRITERIA_EVALUATION.forEach {el->
        println()
        println("")
        val arrOfMinValues = Array<Float>(GLOBAL_COUNT_EXPERT){0f}
        val arrOfMiddleValues = Array(GLOBAL_COUNT_EXPERT){0f}
        val arrOfMaxValues  = Array(GLOBAL_COUNT_EXPERT){0f}

        for (i in 1..GLOBAL_COUNT_EXPERT) {
            
            val currentLimit= findLimitsByName(el.values[i]!!)

            arrOfMiddleValues[i-1] = currentLimit.secondLimit.toFloat()
            arrOfMaxValues[i-1] = currentLimit.thirdLimit.toFloat()
            var tempMinVal = 1f
            if(currentLimit.firstLimit.toFloat()<tempMinVal){
                tempMinVal=currentLimit.firstLimit.toFloat()
            }
            if(currentLimit.secondLimit.toFloat()<tempMinVal){
                tempMinVal=currentLimit.secondLimit.toFloat()
            }
            if(currentLimit.thirdLimit.toFloat()<tempMinVal){
                tempMinVal=currentLimit.thirdLimit.toFloat()
            }
            arrOfMinValues[i-1]=tempMinVal


        }

        var multMinValue = 0f
        var multMiddleValue = 0f
        var multMaxValue = 0f

        for(i in arrOfMinValues.indices){
            if(i==0){
                multMinValue=arrOfMinValues[i]
                multMiddleValue=arrOfMiddleValues[i]
                multMaxValue=arrOfMaxValues[i]
            }else{
                multMinValue*=arrOfMinValues[i]
                multMiddleValue*=arrOfMiddleValues[i]
                multMaxValue*=arrOfMaxValues[i]
            }


        }

        val lValue = arrOfMinValues.min()
        val lshtValue =  multMinValue.pow((1 / 3f))
        val mValue=multMiddleValue.pow((1/3f))
        val ushtValue=multMaxValue.pow((1/3f))
        val uValue=arrOfMaxValues.max()

        GLOBAL_CRITERIA_FUZZY_NUMBERS[index].name=el.name
        GLOBAL_CRITERIA_FUZZY_NUMBERS[index].lValue=lValue
        GLOBAL_CRITERIA_FUZZY_NUMBERS[index].lshtValue=lshtValue
        GLOBAL_CRITERIA_FUZZY_NUMBERS[index].mValue=mValue
        GLOBAL_CRITERIA_FUZZY_NUMBERS[index].ushtValue=ushtValue
        GLOBAL_CRITERIA_FUZZY_NUMBERS[index].uValue=uValue
        index++
    }

}

