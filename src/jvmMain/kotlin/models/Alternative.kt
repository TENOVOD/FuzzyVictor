package models

data class Alternative(
    var id:Int,
    var name:String
){

}

enum class TypeMinMax(
    name:String
){
    MIN(
        name = "MIN"
    ),
    MAX(
        name="MAX"
    )
}