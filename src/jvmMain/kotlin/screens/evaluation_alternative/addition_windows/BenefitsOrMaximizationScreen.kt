package screens.evaluation_alternative.addition_windows

import GLOBAL_FF
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
import data.calculatePerfectValue
import navcontroller.NavController
import screens.elements.BasicButton
import screens.elements.HeaderCell
import screens.elements.LeftSideMainCell
import screens.elements.TableCellWithText

@Composable
fun BenefitsOrMaximization(
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
                    Row {
                        HeaderCell("")
                        HeaderCell("Ідеальні значення")
                    }
                    GLOBAL_FF.forEach {
                        Row {
                            LeftSideMainCell(it.criteriaName)
                            var bestStr = "("
                            var minStr = ""
                            var maxStr = ""
                            it.optimizationValues.forEach {el->
                                bestStr+="$el, "
                            }
                            bestStr+=")"
                            it.minValue.forEach {el->
                                minStr+="$el, "
                            }
                            minStr+=")"
                            it.maxValue.forEach {el->
                                maxStr+="$el, "
                            }
                            maxStr+=")"
                            TableCellWithText(bestStr)

                        }
                    }
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