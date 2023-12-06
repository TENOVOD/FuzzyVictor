package data

import GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT
import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_CRITERIA_AGGREGATED_WEIGHT
import GLOBAL_FF
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_NORMALIZE_FUZZY_DIFFERENCE
import models.BenefitsOrNot

data class MatrixOfMinOrMaxValue(
    var criteriaName: String,
    var minValue: Array<String>,
    var maxValue: Array<String>,
    var optimizationValues: Array<String>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatrixOfMinOrMaxValue

        if (criteriaName != other.criteriaName) return false
        if (!minValue.contentEquals(other.minValue)) return false
        return maxValue.contentEquals(other.maxValue)
    }

    override fun hashCode(): Int {
        var result = criteriaName.hashCode()
        result = 31 * result + minValue.contentHashCode()
        result = 31 * result + maxValue.contentHashCode()
        return result
    }

}


fun calculatePerfectValue() {
    GLOBAL_FF.clear()

    for (c in 1..GLOBAL_COUNT_CRITERIA) {
        val listOfPairIndexAndSum = mutableListOf<Pair<String, Float>>()
        GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach {
            val arrayOfLMR = it.table[c]
            var sum = 0f
            arrayOfLMR?.forEach { el ->
                sum += el.toFloat()
            }
            listOfPairIndexAndSum.add(Pair(it.altName, sum))
        }
        var maxPair = Pair("", 0f)
        var minPair = Pair("", 100f)
        println("ALL PAIRS")
        listOfPairIndexAndSum.forEach {
            println(it)
            if (it.second < minPair.second) {
                minPair = it
            }
            if (it.second > maxPair.second) {
                maxPair = it
            }
        }
        println("END PAIRS")
        println()
        println()
        println("MAX PAIR: $maxPair")
        println("MIN PAIR: $minPair")
        println()
        println()
        val resultValues = MatrixOfMinOrMaxValue("", Array(3) { "" }, Array(3) { "" }, Array(3) { "" })
        GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach {
            if (it.altName == minPair.first) {
                resultValues.criteriaName = GLOBAL_MATRIX_OF_CRITERIA[c - 1].name
                resultValues.minValue = it.table[c]!!
            }
            if (it.altName == maxPair.first) {
                resultValues.criteriaName = GLOBAL_MATRIX_OF_CRITERIA[c - 1].name
                resultValues.maxValue = it.table[c]!!
            }
        }
        if (GLOBAL_MATRIX_OF_CRITERIA[c - 1].type == BenefitsOrNot.NO) {
            resultValues.optimizationValues = resultValues.minValue
        } else {
            resultValues.optimizationValues = resultValues.maxValue
        }

        GLOBAL_FF.add(resultValues)

    }
    println("@@@ALCC")
    GLOBAL_FF.forEach {
        println(it)
        println("MinArr")
        it.optimizationValues.forEach { el ->
            print("$el,")
        }
        println("nnn")

    }
    println("SRWRARA")
}

fun normalizeFuzzyDifference() {
    GLOBAL_NORMALIZE_FUZZY_DIFFERENCE.clear()
    for (a in 1..GLOBAL_COUNT_ALTERNATIVE) {
        val targetAltName = GLOBAL_MATRIX_OF_ALTERNATIVES[a - 1].name
        val mapOfAllNormalizeCriteriaForOneAlternative = mutableMapOf<Int, Array<String>>()
        for (i in 1..GLOBAL_COUNT_CRITERIA) {
            if (GLOBAL_MATRIX_OF_CRITERIA[i - 1].type == BenefitsOrNot.NO) {
                val alternativeWithWeight = GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT[a - 1]
                if (alternativeWithWeight.altName == targetAltName) {
                    val fiStar = alternativeWithWeight.table[i]!!
                    val fi0 = GLOBAL_FF[i - 1].optimizationValues
                    val resultArray = Array<String>(3) { "" }
                    resultArray[0] = (fiStar[0].toFloat() - fi0[2].toFloat()).toString()
                    resultArray[1] = (fiStar[1].toFloat() - fi0[1].toFloat()).toString()
                    resultArray[2] = (fiStar[2].toFloat() - fi0[0].toFloat()).toString()
                    mapOfAllNormalizeCriteriaForOneAlternative[i]=resultArray
                }
            }
            if(GLOBAL_MATRIX_OF_CRITERIA[i - 1].type == BenefitsOrNot.YES){
                val alternativeWithWeight = GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT[a - 1]
                if (alternativeWithWeight.altName == targetAltName) {
                    val fiStar = alternativeWithWeight.table[i]!!
                    val fi0 = GLOBAL_FF[i - 1].optimizationValues
                    val resultArray = Array<String>(3) { "" }
                    resultArray[0] = (fi0[0].toFloat() - fiStar[2].toFloat()).toString()
                    resultArray[1] = (fi0[1].toFloat() - fiStar[1].toFloat()).toString()
                    resultArray[2] = (fi0[2].toFloat() - fiStar[0].toFloat()).toString()
                    mapOfAllNormalizeCriteriaForOneAlternative[i]=resultArray
                }
            }

        }
        GLOBAL_NORMALIZE_FUZZY_DIFFERENCE.add(
            AggregateScore(
                targetAltName,
                mapOfAllNormalizeCriteriaForOneAlternative
            )
        )
    }

    println("START NORMALIZE BY BENEFITS")
    GLOBAL_NORMALIZE_FUZZY_DIFFERENCE.forEach {
        println(it)
        for (i in 1..GLOBAL_COUNT_CRITERIA){
           println("CRITERIA #$i")
            it.table[i]?.forEach {el->
                println(el)
            }
            println("END CRITERIA")
        }
    }
    println("END NORMALIZE BY BENEFITS")

}