package screens.elements

import GLOBAL_EXPERTS_EVALUATION_LIST
import GLOBAL_MATRIX_OF_EXPERTS
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.*

import models.*
import screens.evaluation_criteria.updateMapEvaluation
import screens.presets_screen.settings_of_alternatives.changeGlobalCriteriaMatrix


@Composable
fun RowScope.HeaderCell(
    text: String
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(100.dp)
            .height(60.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontWeight = FontWeight(600)

    )
}

@Composable
fun RowScope.LeftSideMainCell(
    text: String
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(100.dp)
            .height(30.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontWeight = FontWeight(600)

    )
}

@Composable
fun RowScope.TableCell(
    value: String,

    onNewValue: (String) -> Unit,

    ) {
    BasicTextField(
        value = value,
        onValueChange = { onNewValue(it) },
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(100.dp)
            .height(30.dp),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 16.sp
        ),


        )
}

@Composable
fun RowScope.TableCellWithText(
    value: String,
) {
    Text(
        text = value,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(110.dp)
            .height(30.dp),
        textAlign = TextAlign.Center,
        fontSize = 12.sp
    )
}

@Composable
fun RowScope.DropdownDemo(
    criteria: Criteria
) {
    var expanded by remember { mutableStateOf(false) }
    val criteriaValue = if (criteria.type == BenefitsOrNot.NO) 0 else 1
    val items = listOf(BenefitsOrNot.NO.toString(), BenefitsOrNot.YES.toString())
    var selectedIndex by remember { mutableStateOf(criteriaValue) }
    Column {
        Text(
            items[selectedIndex],
            modifier = Modifier.border(1.dp, Color.Black).width(250.dp).height(34.dp).padding(start = 7.dp, top = 4.dp)
                .clickable(onClick = { expanded = true }).background(
                Color.White
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(250.dp).background(
                Color.LightGray
            )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    if (selectedIndex == 1) {
                        changeGlobalCriteriaMatrix(criteria, BenefitsOrNot.YES)
                    } else {
                        changeGlobalCriteriaMatrix(criteria, BenefitsOrNot.NO)
                    }
                }) {
                    Text(text = s)
                }
            }
        }
    }
}

@Composable
fun RowScope.DropdownEvaluationCriteria(
    list:MutableList<String>,
    key:Int,
    criteria: CriteriaWithExpertEval
) {
    var expanded by remember { mutableStateOf(false) }
    var criteriaValue = 0
    for (i in list.indices){
        if(list[i]==criteria.values[key]){
            criteriaValue=i
        }
    }
    val items = list.toList()
    var selectedIndex by remember { mutableStateOf(criteriaValue) }
    Column(Modifier
        .border(1.dp, Color.Black)
        .weight(0.9f)
        .padding(2.dp)
        .width(100.dp)
        .height(30.dp),) {
        Text(
            items[selectedIndex],
            modifier = Modifier.fillMaxWidth().height(30.dp).padding(start = 7.dp, top = 4.dp)
                .clickable(onClick = { expanded = true }).background(
                    Color.White
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(100.dp).background(
                Color.LightGray
            )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    updateMapEvaluation(key,criteria.name,list[selectedIndex])
                    updateAggregatedCriteriaWeightMatrix()
                }) {
                    Text(text = s)
                }
            }
        }
    }
}

@Composable
fun RowScope.DropdownChooseExpert(
    selectedIndex:Int,
    expanded:Boolean,
    changeValue:(Int)->Unit,
    onExpandedTrue:()->Unit,
    onExpandedFalse:()->Unit,
) {
    val list = getExpertsNames(GLOBAL_MATRIX_OF_EXPERTS)

    val items = list.toList()

    Column(Modifier
        .border(1.dp, Color.Black)
        .padding(2.dp)
        .width(100.dp)
        .height(30.dp),) {
        Text(
            items[selectedIndex],
            modifier = Modifier.fillMaxWidth().height(30.dp).padding(start = 7.dp, top = 4.dp)
                .clickable(onClick = onExpandedTrue).background(
                    Color.White
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onExpandedFalse,
            modifier = Modifier.width(100.dp).background(
                Color.LightGray
            )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    changeValue(index)
                }) {
                    Text(text = s)
                }
            }
        }
    }
}

@Composable
fun RowScope.DropdownAlternativeEvaluation(
    currentValue:String?,
    aIndex:Int,
    cKey:Int,
    indexOfExpertEvaluationList:Int,
    list:MutableList<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var criteriaValue = 0
    for(i in list.indices){
        if(list[i]==currentValue){
            criteriaValue=i
        }
    }
    //println("BF SELECTED VALUE: $currentValue SELECTED INDEX: ${criteriaValue} VALUE BY INDEX ${list[criteriaValue]}")
    val items = list.toList()
    var selectedIndex by remember { mutableStateOf(criteriaValue) }
    //println("AFT SELECTED VALUE: $currentValue SELECTED INDEX: ${criteriaValue} VALUE BY INDEX ${items[selectedIndex]}")

    Column (Modifier
        .border(1.dp, Color.Black)
        .weight(0.9f)
        .padding(2.dp)
        .width(100.dp)
        .height(30.dp),){
        Text(
            items[selectedIndex],
            modifier = Modifier.fillMaxWidth().height(30.dp).padding(start = 7.dp, top = 4.dp)
                .clickable(onClick = { expanded = true }).background(
                    Color.White
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(100.dp).background(
                Color.LightGray
            )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    println("indexA: $aIndex, cKey: $cKey, indexOfExpertList: $indexOfExpertEvaluationList")
                    println("value: ${GLOBAL_EXPERTS_EVALUATION_LIST[indexOfExpertEvaluationList-1].table[aIndex][cKey]}")
                    GLOBAL_EXPERTS_EVALUATION_LIST[indexOfExpertEvaluationList-1].table[aIndex][cKey] = items[selectedIndex]
                    getAggregateStore()
                    updateAggAlternativeWeightMatrix()
                    calculatePerfectValue()
                    normalizeFuzzyDifference()
                    calculateS()
                    calculateR()
                    calculateQ()
                    calculateDefuzzification()
                }) {
                    Text(text = s)
                }
            }
        }
    }
}