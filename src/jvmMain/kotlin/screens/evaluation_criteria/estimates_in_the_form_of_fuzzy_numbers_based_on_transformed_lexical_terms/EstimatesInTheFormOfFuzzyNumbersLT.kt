package screens.evaluation_criteria.estimates_in_the_form_of_fuzzy_numbers_based_on_transformed_lexical_terms

import GLOBAL_CRITERIA_AGGREGATED_WEIGHT
import GLOBAL_CRITERIA_FUZZY_NUMBERS
import GLOBAL_MATRIX_OF_CRITERIA_EVALUATION
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText

@Composable
fun EstimatesFormOfFuzzyNumbersTransformedLTScreen(
    navController: NavController
){
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
                Column (
                    modifier = Modifier.padding(10.dp)
                ){
                    Row(){
                        HeaderCell("")
                        HeaderCell("l")
                        HeaderCell("m'")
                        HeaderCell("r")
                    }
                    GLOBAL_CRITERIA_AGGREGATED_WEIGHT.forEach {
                        Row(){
                            LeftSideMainCell(it.name)
                            TableCellWithText(it.l.toString())
                            TableCellWithText(it.m.toString())
                            TableCellWithText(it.r.toString())
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                BasicButton("Повернутись до оцінки критеріїв") {
                    navController.navigate(Screen.EvaluationCriteria.name)
                }
            }
        }

    }

}