package models

import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_EXPERTS
import GLOBAl_ALTERNATIVE_LT

data class ExpertAlternativeEvaluation(
    val expertId: Int,
    val table: Array<MutableMap<Int,String>>
) {
}

fun setEmptyListExpertsEvaluation(

): MutableList<ExpertAlternativeEvaluation> {
    val result = mutableListOf<ExpertAlternativeEvaluation>()
    for(i in GLOBAL_MATRIX_OF_EXPERTS){
        val expertId = i.id
        val arrayTable = Array<MutableMap<Int,String>>(GLOBAL_COUNT_ALTERNATIVE){ mutableMapOf<Int,String>(1 to "") }
        val minAlternativeLT = GLOBAl_ALTERNATIVE_LT[0].fullName
        for(a in GLOBAL_MATRIX_OF_ALTERNATIVES.indices){
            for(c in 1..GLOBAL_COUNT_CRITERIA){
                arrayTable[a][c] = minAlternativeLT
            }
        }
        result.add(ExpertAlternativeEvaluation(expertId,arrayTable))
    }
    println("**Result setEmptyListExpertsEvaluation**")
    result.forEach {
        println(it)

    }
    println("****")
    return result
}
