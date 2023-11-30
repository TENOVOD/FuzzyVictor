package data

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_EXPERTS_EVALUATION_LIST
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAl_ALTERNATIVE_LT

data class AggregateScore(
    var altName: String,
    var table: MutableMap<Int, Array<String>>
)

fun getEmptyAggregationStore(): MutableList<AggregateScore> {
    val result = mutableListOf<AggregateScore>()
    for (a in 1..GLOBAL_COUNT_ALTERNATIVE) {
        val altName = GLOBAL_MATRIX_OF_ALTERNATIVES[a - 1].name
        val tempTable = mutableMapOf<Int, Array<String>>(1 to Array<String>(GLOBAL_COUNT_EXPERT) { "1" })
        for (c in 1..GLOBAL_COUNT_CRITERIA) {
            tempTable[c]=Array<String>(GLOBAL_COUNT_EXPERT) { "1" }

        }
        result.add(AggregateScore(altName, tempTable))
    }
    result.forEach {

    }
    return result
}


fun getAggregateStore(){

    for(a in 1..GLOBAL_COUNT_ALTERNATIVE){
        for (gle in GLOBAL_EXPERTS_EVALUATION_LIST.indices){
            for(c in 1..GLOBAL_COUNT_CRITERIA){
                val sr = GLOBAL_EXPERTS_EVALUATION_LIST[gle].table[a-1][c]!!
                GLOBAL_AGGREGATE_SCORE[a-1].table[c]?.set(gle, getShortNameAlternativeLTByFullName(sr))
                val altName = GLOBAL_MATRIX_OF_ALTERNATIVES[a - 1].name
                GLOBAL_AGGREGATE_SCORE[a-1].altName=altName


            }
        }

    }

    GLOBAL_AGGREGATE_SCORE.forEach {
        println(it)
        println("Array:::")
        it.table.forEach {
            println("New arr:")
            it.value.forEach {
                print(it)
            }
            println()
        }
        println("--ARR")
    }
}

fun getShortNameAlternativeLTByFullName(
    fullName:String
):String{
    var result=""
    for (i in GLOBAl_ALTERNATIVE_LT.indices){
        if(GLOBAl_ALTERNATIVE_LT[i].fullName==fullName){
            result=GLOBAl_ALTERNATIVE_LT[i].shortName
        }
    }

    return result
}