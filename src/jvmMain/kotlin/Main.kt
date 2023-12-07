import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.*
import models.*
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import navcontroller.rememberNavController
import screens.ResultScreen
import screens.elements.HeadersButton
import screens.evaluation_alternative.EvaluationAlternativeScreen
import screens.evaluation_alternative.addition_windows.*
import screens.evaluation_criteria.EvaluationCriteria
import screens.evaluation_criteria.criterion_evaluation_in_the_form_of_fuzzy_triangular_numbers.CriteriaEvalFuzzyTriangularNumbersScreen
import screens.evaluation_criteria.estimates_in_the_form_of_fuzzy_numbers_based_on_transformed_lexical_terms.EstimatesFormOfFuzzyNumbersTransformedLTScreen
import screens.evaluation_criteria.normalizeAlternativeLT
import screens.presets_screen.PresentScreenView
import screens.presets_screen.alternatives_names.AlternativesName
import screens.presets_screen.expert_count.ExpertsName
import screens.presets_screen.settings_of_alternatives.SettingsOfAlternativesScreen


var GLOBAl_CRITERIA_LT = setFor3LinguisticTerm
var GLOBAl_ALTERNATIVE_LT = setFor3AlternativeTerm

var GLOBAL_COUNT_EV_CRITERIA=3
var GLOBAL_COUNT_EV_ALTERNATIVE=3

var GLOBAL_COUNT_CRITERIA = 2
var GLOBAL_COUNT_ALTERNATIVE = 2
var GLOBAL_COUNT_EXPERT = 1

var GLOBAL_MATRIX_OF_CRITERIA= defaultListFor2Criteria
var GLOBAL_MATRIX_OF_ALTERNATIVES= setFor2Alternatives
var GLOBAL_MATRIX_OF_EXPERTS= setForExpert

//VICTOR

//CRITERIA AGGREGATED WEIGHT
var GLOBAL_CRITERIA_AGGREGATED_WEIGHT= mutableListOf<AggregatedCriteriaWeight>()
var GLOBAL_AGGREGATE_SCORE =getEmptyAggregationStore()
var GLOBAL_ALTERNATIVE_AGGREGATED_WEIGHT= mutableListOf<AggregateScore>()

var GLOBAL_FF = mutableListOf<MatrixOfMinOrMaxValue>()

var GLOBAL_NORMALIZE_FUZZY_DIFFERENCE = mutableListOf<AggregateScore>()

var GLOBAL_MATRIX_OF_S = AggregateScore("", mutableMapOf<Int,Array<String>>())
var GLOBAL_MATRIX_OF_R = AggregateScore("", mutableMapOf<Int,Array<String>>())
var GLOBAL_MATRIX_OF_Q = AggregateScore("", mutableMapOf<Int,Array<String>>())
var GLOBAL_V = 0.5f

var GLOBAL_DEFUZZIFICATION_MATRIX_OF_S = mutableListOf<Pair<String,Float>>()
var GLOBAL_DEFUZZIFICATION_MATRIX_OF_R  = mutableListOf<Pair<String,Float>>()
var GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q  = mutableListOf<Pair<String,Float>>()

var GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING = listOf<Pair<String,Float>>()
var GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING  = listOf<Pair<String,Float>>()
var GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING  = listOf<Pair<String,Float>>()

//SECOND PAGE (EVALUATION CRITERIA)
var GLOBAL_MATRIX_OF_CRITERIA_EVALUATION = addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA,GLOBAL_COUNT_EXPERT)
var GLOBAL_NORMALIZE_OF_CRITERIA_LT = mutableListOf<LinguisticTermCell>()
var GLOBAL_CRITERIA_FUZZY_NUMBERS = getEmptyCriteriaFuzzyNumbers()



//THIRD SCREEN (EVALUATION ALTERNATIVE)
var GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()



var GLOBAL_NORMALIZE_OF_ALTERNATIVE_LT = mutableListOf<LinguisticTermCell>()
var GLOBAL_ALL_ALTERNATIVE_FUZZY_NUMBERS = mutableListOf<Pair<String,AlternativeAndCriteriaFuzzyNumbers>>()



@Composable
@Preview
fun App() {
    
    //PresentScreenView()
    val prep = Screen.values().toList()
    val screens = prep.dropLast(11)
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier.width(1900.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Row() {
                        HeadersButton("Попередні налаштування"){
                            navController.navigate(Screen.HomeScreen.name)
                        }
                        HeadersButton("Оцінка критеріїв"){
                            navController.navigate(Screen.EvaluationCriteria.name)
                        }
                        HeadersButton("Оцінка альтернатив"){
                            navController.navigate(Screen.EvaluationAlternative.name)
                        }
                        HeadersButton("Результати "){
                            navController.navigate(Screen.ResultScreen.name)
                        }
                        HeadersButton("DATASET"){
                            GLOBAl_CRITERIA_LT = setFor7LinguisticTerm
                            GLOBAl_ALTERNATIVE_LT = setFor7AlternativeTerm

                            GLOBAL_COUNT_EV_CRITERIA = 7
                            GLOBAL_COUNT_EV_ALTERNATIVE = 7

                            GLOBAL_COUNT_CRITERIA = 5
                            GLOBAL_COUNT_ALTERNATIVE = 5
                            GLOBAL_COUNT_EXPERT = 4

                            GLOBAL_MATRIX_OF_CRITERIA = mutableListOf(
                                Criteria(1, "Частота пам’яті", BenefitsOrNot.YES),
                                Criteria(2, "Частота ядра", BenefitsOrNot.NO),
                                Criteria(3, "Охолодження", BenefitsOrNot.YES),
                                Criteria(4, "Обсяг пам’яті", BenefitsOrNot.YES),
                                Criteria(5, "Шина", BenefitsOrNot.NO),
                            )
                            GLOBAL_MATRIX_OF_ALTERNATIVES = mutableListOf(
                                Alternative(1, "EVGA FTW3"),
                                Alternative(2, "Sap. RX7900XT"),
                                Alternative(3, "GV-N4070AERO"),
                                Alternative(4, "NED407TU19K9"),
                                Alternative(5, "GV-N407TAERO"),
                            )
                            GLOBAL_MATRIX_OF_EXPERTS = mutableListOf(
                                Expert(1, "Сашко"),
                                Expert(2, "Кирил"),
                                Expert(3, "Володя"),
                                Expert(4, "Петро"),
                            )
                            GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                            GLOBAL_MATRIX_OF_CRITERIA_EVALUATION =
                                addNewCriteriaOrExpert(GLOBAL_COUNT_CRITERIA, GLOBAL_COUNT_EXPERT)
                            GLOBAL_CRITERIA_FUZZY_NUMBERS = getEmptyCriteriaFuzzyNumbers()
                            GLOBAL_MATRIX_OF_CRITERIA_EVALUATION = mutableListOf(
                                CriteriaWithExpertEval(
                                    "Частота пам’яті",
                                    mutableMapOf(
                                        1 to "Medium high",
                                        2 to "Medium",
                                        3 to "Medium high",
                                        4 to "Medium",
                                    )
                                ),
                                CriteriaWithExpertEval(
                                    "Частота ядра",
                                    mutableMapOf(
                                        1 to "Medium high",
                                        2 to "Medium",
                                        3 to "Very high",
                                        4 to "Medium high",
                                    )
                                ),
                                CriteriaWithExpertEval(
                                    "Охолодження",
                                    mutableMapOf(
                                        1 to "High",
                                        2 to "Very high",
                                        3 to "Medium",
                                        4 to "High",
                                    )
                                ),
                                CriteriaWithExpertEval(
                                    "Обсяг пам’яті",
                                    mutableMapOf(
                                        1 to "Medium",
                                        2 to "High",
                                        3 to "High",
                                        4 to "High",
                                    )
                                ),
                                CriteriaWithExpertEval(
                                    "Шина",
                                    mutableMapOf(
                                        1 to "High",
                                        2 to "Very high",
                                        3 to "High",
                                        4 to "Very high",
                                    )
                                ),
                            )
                            updateAggregatedCriteriaWeightMatrix()
                            GLOBAL_EXPERTS_EVALUATION_LIST = setEmptyListExpertsEvaluation()
                            GLOBAL_EXPERTS_EVALUATION_LIST = mutableListOf(
                                ExpertAlternativeEvaluation(
                                    1,
                                    arrayOf(
                                        mutableMapOf(
                                            1 to "Medium low",
                                            2 to "Low",
                                            3 to "High",
                                            4 to "Very high",
                                            5 to "Very high"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium",
                                            2 to "Medium high",
                                            3 to "Very high",
                                            4 to "High",
                                            5 to "Medium high"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "High",
                                            3 to "Medium",
                                            4 to "Medium low",
                                            5 to "Medium low"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Very high",
                                            3 to "Medium",
                                            4 to "Medium low",
                                            5 to "Medium low"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "High",
                                            3 to "Medium",
                                            4 to "Medium low",
                                            5 to "Medium low"
                                        ),
                                    )
                                ),
                                ExpertAlternativeEvaluation(
                                    2,
                                    arrayOf(
                                        mutableMapOf(
                                            1 to "Medium",
                                            2 to "Medium low",
                                            3 to "Medium high",
                                            4 to "Very high",
                                            5 to "Very high"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium",
                                            2 to "Medium high",
                                            3 to "Medium high",
                                            4 to "Medium high",
                                            5 to "Medium low"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "High",
                                            3 to "Medium",
                                            4 to "Medium",
                                            5 to "Medium low"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Very high",
                                            3 to "Medium high",
                                            4 to "Medium",
                                            5 to "Low"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Very high",
                                            3 to "Medium",
                                            4 to "Medium",
                                            5 to "Low"
                                        ),
                                    )
                                ),
                                ExpertAlternativeEvaluation(
                                    3,
                                    arrayOf(
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Medium low",
                                            3 to "Medium high",
                                            4 to "Very high",
                                            5 to "Very high"
                                        ),
                                        mutableMapOf(
                                            1 to "High",
                                            2 to "High",
                                            3 to "Medium",
                                            4 to "Medium",
                                            5 to "High"
                                        ),
                                        mutableMapOf(
                                            1 to "High",
                                            2 to "High",
                                            3 to "Medium",
                                            4 to "Medium high",
                                            5 to "Medium"
                                        ),
                                        mutableMapOf(
                                            1 to "High",
                                            2 to "Very high",
                                            3 to "Medium high",
                                            4 to "Medium",
                                            5 to "Medium"
                                        ),
                                        mutableMapOf(
                                            1 to "High",
                                            2 to "High",
                                            3 to "Medium high",
                                            4 to "Medium",
                                            5 to "Medium"
                                        ),
                                    )
                                ),
                                ExpertAlternativeEvaluation(
                                    4,
                                    arrayOf(
                                        mutableMapOf(
                                            1 to "Medium",
                                            2 to "Low",
                                            3 to "High",
                                            4 to "High",
                                            5 to "Very high"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Medium high",
                                            3 to "Medium high",
                                            4 to "High",
                                            5 to "Medium high"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Medium high",
                                            3 to "Medium high",
                                            4 to "Medium",
                                            5 to "Medium high"
                                        ),
                                        mutableMapOf(
                                            1 to "High",
                                            2 to "Very high",
                                            3 to "High",
                                            4 to "Medium",
                                            5 to "Medium"
                                        ),
                                        mutableMapOf(
                                            1 to "Medium high",
                                            2 to "Medium high",
                                            3 to "Very high",
                                            4 to "Medium",
                                            5 to "Medium"
                                        ),
                                    )
                                ),
                            )
                            GLOBAL_AGGREGATE_SCORE = getEmptyAggregationStore()
                            normalizeAlternativeLT()
                            getAggregateStore()
                            updateAggAlternativeWeightMatrix()
                            calculatePerfectValue()
                            normalizeFuzzyDifference()
                            calculateS()
                            calculateR()
                            calculateQ()
                            calculateDefuzzification()
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        CustomNavigationHost(navController = navController)
                    }
                }
            }
        }
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {

        App()
    }
}

/**
 * Screens
 */
enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Settings",
        icon = Icons.Filled.Settings
    ),
    EvaluationCriteria(
        label = "Ev.Crit.",
        icon = Icons.Filled.AddCircle
    ),
    EvaluationAlternative(
        label = "Ev.Altern.",
        icon = Icons.Filled.AddCircle
    ),
    ResultScreen(
        label = "Result",
        icon = Icons.Filled.Done
    ),
    CriteriaSettings(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    AlternativesName(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    ExpertsName(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    FuzzyTriangularNumbers(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    EstimatesFormOfFuzzyNumbersTransformedLTScreen(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    AggregateScoreScreen(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    EstimatesInTheFormOfFuzzyTriangularNumbersScreen(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    EstimatesInTheFormOfFuzzyNumberScreen(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    BenefitsOrMaximizationScreen(
        label = "BenefitsOrMaximization",
        icon = Icons.Filled.Done
    ),
    NormalizeFuzzyDifferenceScreen(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    ),
    SRQScreen(
        label = "Criteria settings",
        icon = Icons.Filled.Done
    )

}


@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavigationHost(navController) {
        composable(Screen.HomeScreen.name) {
            PresentScreenView(navController)
        }

        composable(Screen.EvaluationCriteria.name) {
            EvaluationCriteria(navController)
        }

        composable(Screen.CriteriaSettings.name){
            SettingsOfAlternativesScreen(navController)
        }
        composable(Screen.AlternativesName.name){
            AlternativesName(navController)
        }
        composable(Screen.ExpertsName.name){
            ExpertsName(navController)
        }
        composable(Screen.FuzzyTriangularNumbers.name){
            CriteriaEvalFuzzyTriangularNumbersScreen(navController)
        }
        composable(Screen.EstimatesFormOfFuzzyNumbersTransformedLTScreen.name){
            EstimatesFormOfFuzzyNumbersTransformedLTScreen(navController)
        }
        composable(Screen.EvaluationAlternative.name){
            EvaluationAlternativeScreen(navController)
        }
        composable(Screen.AggregateScoreScreen.name){
            AggregateScoreScreen(navController)
        }
        composable(Screen.EstimatesInTheFormOfFuzzyTriangularNumbersScreen.name){
            EstimatesInTheFormOfFuzzyTriangularNumbersScreen(navController)
        }
        composable(Screen.EstimatesInTheFormOfFuzzyNumberScreen.name){
            EstimatesInTheFormOfFuzzyNumber(navController)
        }
        composable(Screen.BenefitsOrMaximizationScreen.name){
            BenefitsOrMaximization(navController)
        }
        composable(Screen.NormalizeFuzzyDifferenceScreen.name){
            NormalizeFuzzyDifferenceScreen(navController)
        }
        composable(Screen.SRQScreen.name){
            SRQScreen(navController)
        }
        composable(Screen.ResultScreen.name){
            ResultScreen(navController)
        }

    }.build()
}