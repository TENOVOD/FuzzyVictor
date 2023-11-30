package screens.evaluation_criteria.criterion_evaluation_in_the_form_of_fuzzy_triangular_numbers

import GLOBAL_COUNT_EXPERT
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import GLOBAL_NORMALIZE_OF_CRITERIA_LT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.LinguisticTermCell
import navcontroller.NavController
import screens.elements.*
import screens.evaluation_criteria.normalizeCriteriaLT

@Composable
fun CriteriaEvalFuzzyTriangularNumbersScreen(
    navController: NavController
){

    Box(
        modifier = Modifier.fillMaxSize().padding(start = 100.dp)
    ) {
        Column {
            Surface(
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier
                    .padding(15.dp)
                    .width(1750.dp)
                    .height(800.dp)
                    .fillMaxHeight(0.5f),
                color = Color.Transparent,
            ) {
                Column (
                    modifier = Modifier.padding(10.dp)
                ){
                    Row(){
                        HeaderCell("")
                        for(i in 1..GLOBAL_COUNT_EXPERT){
                            HeaderCell("Expert #$i")
                        }
                    }
                    val list = mutableListOf<String>()
                    GLOBAL_NORMALIZE_OF_CRITERIA_LT.forEach {
                        list.add(it.fullName)
                    }
                    GLOBAL_MATRIX_OF_CRITERIA_EVALUATION.forEach { el->
                        Row(horizontalArrangement = Arrangement.SpaceEvenly){
                            LeftSideMainCell(el.name)
                            for(i in 1..GLOBAL_COUNT_EXPERT){
                                val currentLimit= findLimitsByName(el.values[i]!!)

                                TableCellWithText("(${currentLimit.firstLimit}, ${currentLimit.secondLimit}, ${currentLimit.thirdLimit})")
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                BasicButton("GO TO PREVIOUS PAGE") {
                    navController.navigate(Screen.EvaluationCriteria.name)
                }
            }
        }

    }

}

fun findLimitsByName(name:String): LinguisticTermCell {
    normalizeCriteriaLT()

    val result = GLOBAL_NORMALIZE_OF_CRITERIA_LT.find {
        //print(" FULL name ${it.fullName} ${it.firstLimit}, ${it.secondLimit}")
        it.fullName==name

    }!!
    return result
}