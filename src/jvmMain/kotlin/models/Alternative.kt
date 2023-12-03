package models

data class Alternative(
    var id:Int,
    var name:String
){

}

enum class BenefitsOrNot(
    name:String
){
    YES(
        name = "YES"
    ),
    NO(
        name="NO"
    )
}