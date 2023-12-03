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

    println("START ALTERNATIVE EVAL")
    GLOBAL_AGGREGATE_SCORE.forEach {
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
//    GLOBAL_FF.clear()
//    GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach {
//        println(it)
//        var array = Array<Float>(GLOBAL_COUNT_CRITERIA) { 0f }
//        for (j in 1..GLOBAL_COUNT_CRITERIA) {
//            //println("FOR CRITERIA #$j")
//            var sum = 0f
//            it.table[j]?.forEach { ent ->
//                sum += ent.toFloat()
//            }
//            array[j - 1] = sum
//            //println()
//        }
//        val minElement = array.min()
//        val maxElement = array.max()
//        var indexOfMinElement = 0
//        var indexOfMaxElement = 0
//        for (i in array.indices) {
//            if (array[i] == minElement) {
//                indexOfMinElement = i
//            }
//            if (array[i] == maxElement) {
//                indexOfMaxElement = i
//            }
//        }
//        val arrayOfMin = Array(3) { "" }
//        val arrayOfMax = Array(3) { "" }
//        for (j in 1..GLOBAL_COUNT_CRITERIA) {
//            var counter = 0
//            if (indexOfMinElement == (j - 1)&& indexOfMaxElement!=indexOfMinElement) {
//                it.table[j]?.forEach { ent ->
//                    println("counter: $counter")
//                    if(counter<3){
//                        arrayOfMin[counter] = ent
//                    }
//                    println("i pll")
//                    counter++
//                }
//            }
//            if (indexOfMaxElement == (j - 1)&& indexOfMaxElement!=indexOfMinElement) {
//                it.table[j]?.forEach { ent ->
//                    if(counter<3){
//                        arrayOfMax[counter] = ent
//                    }
//                    counter++
//                }
//            }
//            if (indexOfMaxElement==indexOfMinElement){
//                it.table[j]?.forEach { ent ->
//                    if(counter<3){
//                        arrayOfMax[counter] = ent
//                        arrayOfMin[counter] = ent
//                    }
//                    counter++
//                }
//            }
//
//        }
//        val map = mutableMapOf<Int, Array<String>>()
//        map[1] = arrayOfMin
//        map[2] = arrayOfMax
//        GLOBAL_FF.add(
//            AggregateScore(
//                it.altName,
//                map
//            )
//        )
//
//
//    }
//    println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
//    GLOBAL_FF.forEach {
//        println(it)
//        for(i in 1..GLOBAL_COUNT_CRITERIA){
//             it.table[i]!!.forEach {
//                 print("$it,")
//             }
//            println()
//
//        }
//    }
    println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
}