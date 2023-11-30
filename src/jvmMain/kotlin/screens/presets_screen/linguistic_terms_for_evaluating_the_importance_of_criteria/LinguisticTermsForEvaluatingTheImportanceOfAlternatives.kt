package screens.presets_screen.linguistic_terms_for_evaluating_the_importance_of_criteria

import GLOBAl_ALTERNATIVE_LT
import androidx.compose.runtime.*
import models.LinguisticTermCell
import screens.presets_screen.updateDataInList

@Composable
fun updateDataAlternativeMatrix(
    data: MutableList<LinguisticTermCell>
) {
    println("UPDATE ALTERNATIVE Data: $data")
    data.forEach { el ->
        var remeFullName by remember { mutableStateOf(el.fullName) }
        var remeShortName by remember { mutableStateOf(el.shortName) }
        var remeFirstLimit by remember { mutableStateOf(el.firstLimit) }
        var remeSecondLimit by remember { mutableStateOf(el.secondLimit) }
        var remeThirdLimit by remember { mutableStateOf(el.thirdLimit) }

        setMatrixOfLTForEvaluatingTheImportanceOfCriteria(
            fullName = remeFullName,
            shortName = remeShortName,
            firstLimit = remeFirstLimit,
            secondLimit = remeSecondLimit,
            thirdLimit = remeThirdLimit,
            changeFullName = {
                val index = updateDataInList(data, oldFullName = remeFullName)
                remeFullName = it
                GLOBAl_ALTERNATIVE_LT.set(
                    index,
                    LinguisticTermCell(remeFullName, remeShortName, remeFirstLimit, remeSecondLimit, remeThirdLimit)
                )
            },
            changeShortName = {
                val index = updateDataInList(data, oldFullName = remeFullName)
                println("INDEX=$index")
                remeShortName = it
                GLOBAl_ALTERNATIVE_LT.set(
                    index,
                    LinguisticTermCell(remeFullName, remeShortName, remeFirstLimit, remeSecondLimit, remeThirdLimit)
                )
            },
            changeFirstLimit = {
                val index = updateDataInList(data, oldFullName = remeFullName)
                remeFirstLimit = it
                GLOBAl_ALTERNATIVE_LT.set(
                    index,
                    LinguisticTermCell(remeFullName, remeShortName, remeFirstLimit, remeSecondLimit, remeThirdLimit)
                )
            },
            changeSecondLimit = {
                val index = updateDataInList(data, oldFullName = remeFullName)
                remeSecondLimit = it
                GLOBAl_ALTERNATIVE_LT.set(
                    index,
                    LinguisticTermCell(remeFullName, remeShortName, remeFirstLimit, remeSecondLimit, remeThirdLimit)
                )
            },
            changeThirdLimit = {
                val index = updateDataInList(data, oldFullName = remeFullName)
                remeThirdLimit = it
                GLOBAl_ALTERNATIVE_LT.set(
                    index,
                    LinguisticTermCell(remeFullName, remeShortName, remeFirstLimit, remeSecondLimit, remeThirdLimit)
                )
            }

        )
    }
}