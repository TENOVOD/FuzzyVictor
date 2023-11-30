package models

data class Expert(
    val id:Int,
    var name:String
) {
}

fun getExpertsNames(list:MutableList<Expert>): MutableList<String> {
    val result = mutableListOf<String>()
    for (e in list){
        result.add(e.name)
    }
    return result
}