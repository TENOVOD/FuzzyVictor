package models

import GLOBAL_MATRIX_OF_CRITERIA

data class Criteria(
    val id:Int,
    var name:String,
    var type:TypeMinMax
)

fun getCriteriaById(
    id:Int
):Criteria{
    val index = 0
    for(i in GLOBAL_MATRIX_OF_CRITERIA.indices){
        if(GLOBAL_MATRIX_OF_CRITERIA[i].id==id){
            return GLOBAL_MATRIX_OF_CRITERIA[i]
        }
    }
    return GLOBAL_MATRIX_OF_CRITERIA[0]
}