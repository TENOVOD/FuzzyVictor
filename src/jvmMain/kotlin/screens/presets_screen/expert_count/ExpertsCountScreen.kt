package screens.presets_screen.expert_count

import GLOBAL_MATRIX_OF_EXPERTS
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
import navcontroller.NavController
import screens.elements.HeaderCell
import screens.elements.TableCell

@Composable
fun ExpertsName(
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(start = 100.dp)
        ){
            Surface(
                shape = RoundedCornerShape(size = 5.dp),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier
                    .padding(15.dp)
                    .width(250.dp)
                    .fillMaxHeight(0.5f)
                ,
                color = Color.Transparent,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Row {
                        HeaderCell("Criteria name")
                        HeaderCell("Optimal value")
                    }
                    GLOBAL_MATRIX_OF_EXPERTS.forEach { el->

                        Row(modifier = Modifier
                            .width(500.dp)
                        ){
                            var alternativeName by remember { mutableStateOf(el.name) }
                            TableCell(alternativeName){
                                alternativeName=it
                                changeAllGlobalExpertsNameById(el.id,alternativeName)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            println(GLOBAL_MATRIX_OF_EXPERTS)
                            navController.navigate(Screen.HomeScreen.name)
                        }) {
                        Text("Go to main page")
                    }

                }

            }
        }
    }
}

fun updateTableByExpertsCount(
    count:Int
){
    when(count){
        1->{
            GLOBAL_MATRIX_OF_EXPERTS= setForExpert
        }
        2->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor2Experts
        }
        3->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor3Experts
        }
        4->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor4Experts
        }
        5->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor5Experts
        }
        6->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor6Experts
        }
        7->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor7Experts
        }
        8->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor8Experts
        }
        9->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor9Experts
        }
        10->{
            GLOBAL_MATRIX_OF_EXPERTS= setFor10Experts
        }
    }
}

fun changeAllGlobalExpertsNameById(
    id:Int,
    name:String
){
    for(i in GLOBAL_MATRIX_OF_EXPERTS.indices){
        if(GLOBAL_MATRIX_OF_EXPERTS[i].id==id){
            GLOBAL_MATRIX_OF_EXPERTS[i].name=name
        }
    }

}