package models

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_CRITERIA_AGGREGATED_WEIGHT
import GLOBAL_EXPERTS_EVALUATION_LIST
import GLOBAL_FF
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import data.AggregateScore
import screens.evaluation_alternative.addition_windows.getLimitsInArrayByShortName
import screens.evaluation_criteria.criterion_evaluation_in_the_form_of_fuzzy_triangular_numbers.findLimitsByName

data class AggregatedCriteriaWeight(
    var name: String,
    var l: Float,
    var m: Float,
    var r: Float
)

fun updateAggregatedCriteriaWeightMatrix() {

    GLOBAL_CRITERIA_AGGREGATED_WEIGHT.clear()
    GLOBAL_MATRIX_OF_CRITERIA_EVALUATION.forEach { entry ->
        val firstLimits = Array(GLOBAL_COUNT_EXPERT) { 0f }
        val secondLimits = Array(GLOBAL_COUNT_EXPERT) { 0f }
        val thirdLimits = Array(GLOBAL_COUNT_EXPERT) { 0f }

        for (i in 1..GLOBAL_COUNT_EXPERT) {
            val temp = findLimitsByName(entry.values[i]!!)
            firstLimits[i - 1] = temp.firstLimit.toFloat()
            secondLimits[i - 1] = temp.secondLimit.toFloat()
            thirdLimits[i - 1] = temp.thirdLimit.toFloat()
        }

        val l = firstLimits.min()
        var m = 0f
        val r = thirdLimits.max()

        secondLimits.forEach {
            m += it
        }
        m /= GLOBAL_COUNT_EXPERT

        GLOBAL_CRITERIA_AGGREGATED_WEIGHT.add(
            AggregatedCriteriaWeight(
                entry.name,
                l,
                m,
                r
            )
        )
    }
    println(GLOBAL_CRITERIA_AGGREGATED_WEIGHT)
}

fun updateAggAlternativeWeightMatrix() {
    GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.clear()



    GLOBAL_AGGREGATE_SCORE.forEach { entry ->

        val map = mutableMapOf<Int, Array<String>>()


        for (i in 1..GLOBAL_COUNT_CRITERIA) {
            val array = entry.table[i]
            val firstLimits = Array(GLOBAL_COUNT_EXPERT) { 0f }
            val secondLimits = Array(GLOBAL_COUNT_EXPERT) { 0f }
            val thirdLimits = Array(GLOBAL_COUNT_EXPERT) { 0f }
            var counter = 0
            array!!.forEach {
                val temp = getLimitsInArrayByShortName(it)
                firstLimits[counter] = temp[0].toFloat()
                secondLimits[counter] = temp[1].toFloat()
                thirdLimits[counter] = temp[2].toFloat()
                counter++
            }
            val l = firstLimits.min()
            var m = 0f
            val r = thirdLimits.max()
            secondLimits.forEach {
                m += it
            }
            m /= GLOBAL_COUNT_EXPERT
            map[i] = arrayOf(l.toString(), m.toString(), r.toString())
        }

        GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.add(
            AggregateScore(entry.altName, map)
        )
    }

    GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach {
        println(it)
    }
    println("START ALTERNATIVE EVAL")
    GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach {
        println("Alternative ${it.altName}")
        for(i in 1..GLOBAL_COUNT_CRITERIA){
            println("CRITERIA #$i")
            it.table[i]!!.forEach {el->
                print("$el,")
            }
            println()
        }
        println(it)

    }
    println("END ALTERNATIVE EVAL")
    println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
}