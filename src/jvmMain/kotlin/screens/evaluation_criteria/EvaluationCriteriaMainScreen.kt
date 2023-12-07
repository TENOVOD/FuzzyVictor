package screens.evaluation_criteria

import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import GLOBAL_MATRIX_OF_EXPERTS
import GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT
import GLOBAL_NORMALIZE_OF_CRITERIA_LT
import GLOBAl_ALTERNATIVE_LT
import GLOBAl_CRITERIA_LT
import Screen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.LinguisticTermCell
import models.calculateCriteriaFuzzyNumbers
import models.getCriteriaById
import models.getEmptyCriteriaFuzzyNumbers
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.DropdownEvaluationCriteria
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.evaluation_criteria.criterion_evaluation_in_the_form_of_fuzzy_triangular_numbers.CriteriaEvalFuzzyTriangularNumbersScreen
import kotlin.math.max

@Composable
fun EvaluationCriteria(
    navController: NavController
) {
    normalizeCriteriaLT()
    val minLTName = GLOBAl_CRITERIA_LT[0].fullName
    if(GLOBAL_MATRIX_OF_CRITERIA_EVALUATION[0].values[1]=="00"){
        for(i in GLOBAL_MATRIX_OF_CRITERIA_EVALUATION.indices){
            for(j in 1..GLOBAL_COUNT_EXPERT){
                GLOBAL_MATRIX_OF_CRITERIA_EVALUATION[i].values[j]=minLTName
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                ,
                color = Color.Transparent,
            ) {
                Column (
                    modifier = Modifier.padding(10.dp)
                ){
                    Row(){
                        HeaderCell("")
                        for(i in 1..GLOBAL_COUNT_EXPERT){
                            HeaderCell(GLOBAL_MATRIX_OF_EXPERTS[i-1].name)
                        }
                    }
                    val list = mutableListOf<String>()
                    GLOBAl_CRITERIA_LT.forEach {
                        list.add(it.fullName)
                    }
                    GLOBAL_MATRIX_OF_CRITERIA_EVALUATION.forEach { el->
                        Row(horizontalArrangement = Arrangement.SpaceEvenly){
                            LeftSideMainCell(el.name)
                            for(i in 1..GLOBAL_COUNT_EXPERT){
                                DropdownEvaluationCriteria(list,i,el)
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                BasicButton("Оцінки критеріїв у вигляді трикутних чисел") {
                    navController.navigate(Screen.FuzzyTriangularNumbers.name)
                }
                BasicButton("Агреговані ваги критеріїв") {
                    calculateCriteriaFuzzyNumbers()
                    navController.navigate(Screen.EstimatesFormOfFuzzyNumbersTransformedLTScreen.name)
                }
            }
        }

    }

}

fun updateMapEvaluation(
    key:Int,
    name:String,
    newValue:String
){
    for(i in GLOBAL_MATRIX_OF_CRITERIA_EVALUATION.indices){
        if(GLOBAL_MATRIX_OF_CRITERIA_EVALUATION[i].name==name){
            try {
                GLOBAL_MATRIX_OF_CRITERIA_EVALUATION[i].values[key]=newValue
                println("DONE NEW VALUE ${GLOBAL_MATRIX_OF_CRITERIA_EVALUATION[i].values[key]} ||| $newValue ")
            }catch (e:Exception){
                println("ERROOOOR UNAVAILBLE KEY")
            }
        }
    }
}

fun normalizeCriteriaLT(){
    GLOBAL_NORMALIZE_OF_CRITERIA_LT.clear()
    var maxValue = 0
    GLOBAl_CRITERIA_LT.forEach {
        if(maxValue<it.firstLimit.toInt()){
            maxValue = it.firstLimit.toInt()
        }
        if(maxValue<it.secondLimit.toInt()){
            maxValue = it.secondLimit.toInt()
        }
        if(maxValue<it.thirdLimit.toInt()){
            maxValue = it.thirdLimit.toInt()
        }
    }
    GLOBAl_CRITERIA_LT.forEach {
        val firstValue = it.firstLimit.toFloat()/maxValue
        val secondValue = it.secondLimit.toFloat()/maxValue
        val thirdValue = it.thirdLimit.toFloat()/maxValue
        GLOBAL_NORMALIZE_OF_CRITERIA_LT.add(
            LinguisticTermCell(it.fullName,it.shortName,firstValue.toString(),secondValue.toString(),thirdValue.toString())
        )
    }
}
fun normalizeAlternativeLT(){
    GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT.clear()
    var maxValue = 0
    GLOBAl_ALTERNATIVE_LT.forEach {
        if(maxValue<it.firstLimit.toInt()){
            maxValue = it.firstLimit.toInt()
        }
        if(maxValue<it.secondLimit.toInt()){
            maxValue = it.secondLimit.toInt()
        }
        if(maxValue<it.thirdLimit.toInt()){
            maxValue = it.thirdLimit.toInt()
        }
    }
    GLOBAl_ALTERNATIVE_LT.forEach {
        val firstValue = it.firstLimit.toFloat()/maxValue
        val secondValue = it.secondLimit.toFloat()/maxValue
        val thirdValue = it.thirdLimit.toFloat()/maxValue
        GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT.add(
            LinguisticTermCell(it.fullName,it.shortName,firstValue.toString(),secondValue.toString(),thirdValue.toString())
        )
    }
}
