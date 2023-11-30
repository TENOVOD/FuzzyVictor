package test

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun JustTextEdit(
    textValue:String,
    changeValue:(String)->Unit
){
    TextField(
        value = textValue,
        onValueChange = changeValue
    )
}

@Composable
fun CountArray(
    superChanger: SuperChanger,
){
   JustTextEdit(superChanger.name){
       superChanger.onNewValue
   }
}

data class SuperChanger(
    var name:String,

){
    val onNewValue:(String)->Unit={
        name=it
    }
}