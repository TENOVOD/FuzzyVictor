package screens.presets_screen.settings_of_alternatives

import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import GLOBAL_MATRIX_OF_CRITERIA
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.*
import models.Criteria
import models.BenefitsOrNot
import models.addNewCriteriaOrExpert
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.DropdownDemo
import screens.elements.HeaderCell
import screens.elements.TableCell


@Composable
fun SettingsOfAlternativesScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    border = BorderStroke(2.dp, Color.Black),
                    modifier = Modifier
                        .padding(15.dp)
                        .width(500.dp)

                    ,
                    color = Color.Transparent,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Row {
                            HeaderCell("Назва критерію")
                            HeaderCell("Benefit?")
                        }
                        GLOBAL_MATRIX_OF_CRITERIA.forEach { el->

                            Row(modifier = Modifier
                                .width(500.dp)
                            ){
                                var criterionName by remember { mutableStateOf(el.name)}
                                TableCell(criterionName){
                                    criterionName=it
                                    changeAllGlobalCriteriaNameById(el.id,criterionName)
                                }
                                DropdownDemo(el)
                            }
                        }


                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                BasicButton(
                    "Повернутись до попередніх налаштувань"
                ){
                    println(GLOBAL_MATRIX_OF_CRITERIA)
                    GLOBAL_MATRIX_OF_CRITERIA_EVALUATION = addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA,GLOBAL_COUNT_EXPERT)
                    navController.navigate(Screen.HomeScreen.name)
                }
            }

        }
    }
}

fun updateTableByCriteria(
    count:Int
){
    when(count){
        2->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor2Criteria
        }
        3->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor3Criteria
        }
        4->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor4Criteria
        }
        5->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor5Criteria
        }
        6->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor6Criteria
        }
        7->{
            GLOBAL_MATRIX_OF_CRITERIA = defaultListFor7Criteria
        }
        8->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor8Criteria
        }
        9->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor9Criteria
        }
        10->{
            GLOBAL_MATRIX_OF_CRITERIA= defaultListFor10Criteria
        }
    }
}


fun changeGlobalCriteriaMatrix(
    el:Criteria,
    type:BenefitsOrNot
){
    for(i in GLOBAL_MATRIX_OF_CRITERIA.indices){
        if(GLOBAL_MATRIX_OF_CRITERIA[i].name==el.name){
            GLOBAL_MATRIX_OF_CRITERIA[i].type=type
        }
    }
}

fun changeAllGlobalCriteriaNameById(
    id:Int,
    name:String
){
    for(i in GLOBAL_MATRIX_OF_CRITERIA.indices){
        if(GLOBAL_MATRIX_OF_CRITERIA[i].id==id){
            GLOBAL_MATRIX_OF_CRITERIA[i].name=name
        }
    }

}