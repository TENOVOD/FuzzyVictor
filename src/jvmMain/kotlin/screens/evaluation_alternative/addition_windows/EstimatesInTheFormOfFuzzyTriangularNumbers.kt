package screens.evaluation_alternative.addition_windows

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT
import GLOBAL_NORMALIZE_OF_CRITERIA_LT
import GLOBAl_ALTERNATIVE_LT
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
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText

@Composable
fun EstimatesInTheFormOfFuzzyTriangularNumbersScreen(
    navController: NavController
){
    Box(
        modifier = Modifier.fillMaxSize().padding(start = 100.dp)
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
                Column (
                    modifier = Modifier.padding(10.dp)
                ){
                    Row(){
                        HeaderCell("")
                        for(i in 1..GLOBAL_COUNT_CRITERIA){
                            HeaderCell(GLOBAL_MATRIX_OF_CRITERIA[i-1].name)
                        }
                    }

                    GLOBAL_AGGREGATE_SCORE.forEach {

                        Row {
                            LeftSideMainCell(it.altName)

                            for(c in 1..GLOBAL_COUNT_CRITERIA){
                                var cellText = "("
                                for(i in 0 until GLOBAL_COUNT_EXPERT){
                                    cellText+="${getLimitsInStringByShortName(it.table[c]!![i])}, "
                                }
                                cellText+=")"
                                TableCellWithText("$cellText")
                            }

                        }
                    }
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
fun getLimitsInStringByShortName(shortName:String):String{
    var result="("
    var temp=LinguisticTermCell("","","","","")
    GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT.forEach {
        if(it.shortName==shortName){
            temp=it
        }
    }
    result="(${temp.firstLimit}, ${temp.secondLimit}, ${temp.thirdLimit})"
    return  result
}

fun getLimitsInArrayByShortName(shortName:String):Array<String>{
    var result=Array<String>(3){""}
    var temp=LinguisticTermCell("","","","","")
    GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT.forEach {
        if(it.shortName==shortName){
            temp=it
        }
    }
    result[0]=temp.firstLimit
    result[1]=temp.secondLimit
    result[2]=temp.thirdLimit
    return  result
}


