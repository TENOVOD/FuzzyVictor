package screens.evaluation_alternative.addition_windows

import GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS
import GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT
import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.AlternativeAndCriteriaFuzzyNumbers
import models.LinguisticTermCell
import models.BenefitsOrNot
import navcontroller.NavController
import screens.elements.*

@Composable
fun EstimatesInTheFormOfFuzzyNumber(
    navController: NavController
) {

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
                    .fillMaxWidth(),
                color = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    println("START NEW PAGE GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT")
                    Row{
                        HeaderCell("")
                        for(i in GLOBAL_MATRIX_OF_CRITERIA){
                            HeaderCell(i.name)
                        }
                    }
                    GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach {
                        Row{
                            LeftSideMainCell(it.altName)
                            for(i in 1..GLOBAL_COUNT_CRITERIA){

                                var tempValue = ""
                                it.table[i]!!.forEach { el->
                                    print("$el,")
                                    tempValue+="$el, "
                                }
                                TableCellWithText(tempValue)
                            }
                        }

                        println(it)

                    }
                    GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT.forEach { el->
                        println(el)
                        for (i in 1..GLOBAL_COUNT_ALTERNATIVE){
                            println("ALTERNATIVE: $i")
                            println("Start array")
                            el.table[i]?.forEach {

                            }
                        }
                    }
                    println("END NEW PAGE GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT")
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                BasicButton("До оцінки альтернатив") {
                    navController.navigate(Screen.EvaluationAlternative.name)
                }

            }
        }

    }
}

fun getLimitsByShortName(shortName: String): Array<Float> {
    val result = Array(3) { 0f }
    var temp = LinguisticTermCell("", "", "", "", "")
    GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT.forEach {
        if (it.shortName == shortName) {
            temp = it
        }
    }
    result[0] = temp.firstLimit.toFloat()
    result[1] = temp.secondLimit.toFloat()
    result[2] = temp.thirdLimit.toFloat()
    return result
}

fun chooseMinOrMaxCriteria(
    criteriaName:String,
    type: BenefitsOrNot
): AlternativeAndCriteriaFuzzyNumbers {
    val tempList = mutableListOf<AlternativeAndCriteriaFuzzyNumbers>()
    GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS.forEach {
        if(criteriaName==it.second.name){
            tempList.add(it.second)
        }
    }
    var lValue = tempList[0].lValue
    var lshtValue = tempList[0].lshtValue
    var mValue = tempList[0].mValue
    var ushtValue = tempList[0].ushtValue
    var uValue = tempList[0].uValue
    tempList.forEach {
        if(type==BenefitsOrNot.YES){
            if(it.lValue<=lValue){
                lValue=it.lValue
            }
            if(it.lshtValue<=lshtValue){
                lshtValue=it.lshtValue
            }
            if(it.mValue<=mValue){
                mValue=it.mValue
            }
            if(it.ushtValue<=ushtValue){
                ushtValue=it.ushtValue
            }
            if(it.uValue<=uValue){
                uValue=it.uValue
            }
        }
        if(type==BenefitsOrNot.NO){
            if(it.lValue>=lValue){
                lValue=it.lValue
            }
            if(it.lshtValue>=lshtValue){
                lshtValue=it.lshtValue
            }
            if(it.mValue>=mValue){
                mValue=it.mValue
            }
            if(it.ushtValue>=ushtValue){
                ushtValue=it.ushtValue
            }
            if(it.uValue>=uValue){
                uValue=it.uValue
            }
        }
    }
    return AlternativeAndCriteriaFuzzyNumbers(criteriaName,lValue,lshtValue,mValue,ushtValue,uValue)


}