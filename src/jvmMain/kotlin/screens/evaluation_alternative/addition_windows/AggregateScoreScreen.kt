package screens.evaluation_alternative.addition_windows

import GLOBAL_AGGREGATE_SCORE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_COUNT_EXPERT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import navcontroller.NavController
import screens.elements.*

@Composable
fun AggregateScoreScreen(
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
                        for(i in 1..GLOBAL_COUNT_CRITERIA){
                            HeaderCell("Crt #$i")
                        }
                    }

                    GLOBAL_AGGREGATE_SCORE.forEach {
                        Row {
                            LeftSideMainCell(it.altName)
                            for(c in 1..GLOBAL_COUNT_CRITERIA){
                                var cellText = "<"
                                for(i in 0 until GLOBAL_COUNT_EXPERT){
                                    cellText+="${it.table[c]!![i]}, "
                                }
                                cellText+=">"
                                TableCellWithText("$cellText")
                            }

                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                BasicButton("GO TO PREVIOUS PAGE") {
                    navController.navigate(Screen.EvaluationAlternative.name)
                }

            }
        }

    }
}