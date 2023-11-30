package models

data class CriteriaWithExpertEval(
    var name:String,
    var values:MutableMap<Int,String>
) {
}

fun addNewCriteriaOrExpert(
    countOfCriteria:Int,
    countOfExpert:Int
):MutableList<CriteriaWithExpertEval>{
    val emptyMatrixOfCriteriaEvaluation = mutableListOf<CriteriaWithExpertEval>()
    for (i in 1..countOfCriteria){
        var criteria= getCriteriaById(i)
        println("Criteria name: ${criteria.name}")
        val evalMutableList = mutableMapOf<Int,String>()
        for(j in 1..countOfExpert){
            evalMutableList[j]="00"
        }
        val temporaryCriteria =CriteriaWithExpertEval(criteria.name,evalMutableList)
        emptyMatrixOfCriteriaEvaluation.add(temporaryCriteria)
    }
    return emptyMatrixOfCriteriaEvaluation
}