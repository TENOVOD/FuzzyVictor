package data

import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_R
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_S
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING
import GLOBAL_MATRIX_OF_ALTERNATIVES
import GLOBAL_MATRIX_OF_Q
import GLOBAL_MATRIX_OF_R
import GLOBAL_MATRIX_OF_S


fun calculateDefuzzification(){
    GLOBAL_DEFUZZIFICATION_MATRIX_OF_S.clear()
    GLOBAL_DEFUZZIFICATION_MATRIX_OF_R.clear()
    GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q.clear()
    for (i in 1..GLOBAL_COUNT_ALTERNATIVE){
        GLOBAL_DEFUZZIFICATION_MATRIX_OF_S.add(GLOBAL_MATRIX_OF_ALTERNATIVES[i-1].name to (GLOBAL_MATRIX_OF_S.table[i]!![0].toFloat()+2*GLOBAL_MATRIX_OF_S.table[i]!![1].toFloat()+GLOBAL_MATRIX_OF_S.table[i]!![2].toFloat())/4)
        GLOBAL_DEFUZZIFICATION_MATRIX_OF_R.add(GLOBAL_MATRIX_OF_ALTERNATIVES[i-1].name to (GLOBAL_MATRIX_OF_R.table[i]!![0].toFloat()+2*GLOBAL_MATRIX_OF_R.table[i]!![1].toFloat()+GLOBAL_MATRIX_OF_R.table[i]!![2].toFloat())/4)
        GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q.add(GLOBAL_MATRIX_OF_ALTERNATIVES[i-1].name to (GLOBAL_MATRIX_OF_Q.table[i]!![0].toFloat()+2*GLOBAL_MATRIX_OF_Q.table[i]!![1].toFloat()+GLOBAL_MATRIX_OF_Q.table[i]!![2].toFloat())/4)
    }

    GLOBAL_DEFUZZIFICATION_MATRIX_OF_S_RANKING = GLOBAL_DEFUZZIFICATION_MATRIX_OF_S.sortedBy {it.second}
    GLOBAL_DEFUZZIFICATION_MATRIX_OF_R_RANKING = GLOBAL_DEFUZZIFICATION_MATRIX_OF_R.sortedBy {it.second}
    GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q_RANKING = GLOBAL_DEFUZZIFICATION_MATRIX_OF_Q.sortedBy {it.second}
}