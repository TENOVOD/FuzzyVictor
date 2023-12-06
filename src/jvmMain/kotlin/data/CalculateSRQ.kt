package data

import GLOBAL_COUNT_ALTERNATIVE
import GLOBAL_COUNT_CRITERIA
import GLOBAL_CRITERIA_AGGREGATED_WEIGHT
import GLOBAL_DEFUZZIFICATION_MATRIX_OF_S
import GLOBAL_MATRIX_OF_Q
import GLOBAL_MATRIX_OF_R
import GLOBAL_MATRIX_OF_S
import GLOBAL_NORMALIZE_FUZZY_DIFFERENCE
import GLOBAL_V

fun calculateS() {
    GLOBAL_MATRIX_OF_S
    println("$$$$ $GLOBAL_CRITERIA_AGGREGATED_WEIGHT")

    for (a in 1..GLOBAL_COUNT_ALTERNATIVE) {
        val resultArrayForOneAlternative = Array(3) { 0f }
        for (i in 0..2) {
            for (c in 1..GLOBAL_COUNT_CRITERIA) {
                //var currentMult = 1
                if (i == 0) {
                    val arrayfi = GLOBAL_NORMALIZE_FUZZY_DIFFERENCE[a - 1].table[c]!!
                    resultArrayForOneAlternative[i] += (
                            GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].l * arrayfi[i].toFloat()
                            )
                    println("Cr#$c, Al#$a i#$i ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].l}*${arrayfi[i]} = ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].l * arrayfi[i].toFloat()}")
                } else if (i == 1) {
                    val arrayfi = GLOBAL_NORMALIZE_FUZZY_DIFFERENCE[a - 1].table[c]!!
                    resultArrayForOneAlternative[i] += (
                            GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].m * arrayfi[i].toFloat()
                            )
                    println("Cr#$c, Al#$a i#$i ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].m}*${arrayfi[i]} = ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].m * arrayfi[i].toFloat()}")
                } else if (i == 2) {
                    val arrayfi = GLOBAL_NORMALIZE_FUZZY_DIFFERENCE[a - 1].table[c]!!
                    resultArrayForOneAlternative[i] += (
                            GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].r * arrayfi[i].toFloat()
                            )
                    println("Cr#$c, Al#$a i#$i ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].r}*${arrayfi[i]} = ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].r * arrayfi[i].toFloat()}")
                }

            }
        }
        val result = arrayOf(
            resultArrayForOneAlternative[0].toString(),
            resultArrayForOneAlternative[1].toString(),
            resultArrayForOneAlternative[2].toString(),
        )
        GLOBAL_MATRIX_OF_S.table[a] = result
    }

    println("START S $GLOBAL_MATRIX_OF_S")
    for (a in 1..GLOBAL_COUNT_ALTERNATIVE) {
        println()
        GLOBAL_MATRIX_OF_S.table[a]?.forEach {
            print("$it,")
        }
        println()
    }
    println("END SSSSS")

}

fun calculateR() {
    println("$$$$ $GLOBAL_CRITERIA_AGGREGATED_WEIGHT")

    for (a in 1..GLOBAL_COUNT_ALTERNATIVE) {
        val resultArrayForOneAlternative = mutableMapOf<Int, Array<Float>>()

        for (i in 0..2) {
            for (c in 1..GLOBAL_COUNT_CRITERIA) {
                //var currentMult = 1
                if (i == 0) {
                    val arrayfi = GLOBAL_NORMALIZE_FUZZY_DIFFERENCE[a - 1].table[c]!!
                    resultArrayForOneAlternative[c] = Array(3) { 0f }
                    resultArrayForOneAlternative[c]!![i] = (
                            GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].l * arrayfi[i].toFloat()
                            )
                    //println("Cr#$c, Al#$a i#$i ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].l}*${arrayfi[i]} = ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].l * arrayfi[i].toFloat()}")
                } else if (i == 1) {
                    val arrayfi = GLOBAL_NORMALIZE_FUZZY_DIFFERENCE[a - 1].table[c]!!
                    resultArrayForOneAlternative[c]!![i] = (
                            GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].m * arrayfi[i].toFloat()
                            )
                    //println("Cr#$c, Al#$a i#$i ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].m}*${arrayfi[i]} = ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].m * arrayfi[i].toFloat()}")
                } else if (i == 2) {
                    val arrayfi = GLOBAL_NORMALIZE_FUZZY_DIFFERENCE[a - 1].table[c]!!
                    resultArrayForOneAlternative[c]!![i] = (
                            GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].r * arrayfi[i].toFloat()
                            )
                    //println("Cr#$c, Al#$a i#$i ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].r}*${arrayfi[i]} = ${GLOBAL_CRITERIA_AGGREGATED_WEIGHT[c - 1].r * arrayfi[i].toFloat()}")
                }

            }
        }

        val firstArray = Array(GLOBAL_COUNT_CRITERIA) { 0f }
        val secondArray = Array(GLOBAL_COUNT_CRITERIA) { 0f }
        val thirdArray = Array(GLOBAL_COUNT_CRITERIA) { 0f }


        for (c in 1..GLOBAL_COUNT_CRITERIA) {

            val temp = resultArrayForOneAlternative[c]!![0]
            if (temp == 0f) {
                firstArray[c - 1] = 0.0f
            } else {
                firstArray[c - 1] = resultArrayForOneAlternative[c]!![0]
            }
            secondArray[c - 1] = resultArrayForOneAlternative[c]!![1]
            thirdArray[c - 1] = resultArrayForOneAlternative[c]!![2]
        }

        val result = arrayOf(
            firstArray.max().toString(),
            secondArray.max().toString(),
            thirdArray.max().toString(),
        )
        GLOBAL_MATRIX_OF_R.table[a] = result
    }

    println("START RRR $GLOBAL_MATRIX_OF_R")
    for (a in 1..GLOBAL_COUNT_ALTERNATIVE) {
        println()
        GLOBAL_MATRIX_OF_R.table[a]?.forEach {
            print("$it,")
        }
        println()
    }
    println("END RRRR")

}

fun calculateQ() {
    val Sstar = Array(3) { 100f }
    val Rstar = Array(3) { 100f }
    var sR = 0f
    var sL = 100f

    var rR = 0f
    var rL = 0f

    for (i in 1..GLOBAL_COUNT_ALTERNATIVE) {
        if (GLOBAL_MATRIX_OF_S.table[i]!![0].toFloat() < Sstar[0]) {
            Sstar[0] = GLOBAL_MATRIX_OF_S.table[i]!![0].toFloat()
            if (GLOBAL_MATRIX_OF_S.table[i]!![0].toFloat() < sL) {
                sL = GLOBAL_MATRIX_OF_S.table[i]!![0].toFloat()
            }
        }
        if (GLOBAL_MATRIX_OF_S.table[i]!![1].toFloat() < Sstar[1]) {
            Sstar[1] = GLOBAL_MATRIX_OF_S.table[i]!![1].toFloat()
        }
        if (GLOBAL_MATRIX_OF_S.table[i]!![2].toFloat() < Sstar[2]) {
            Sstar[2] = GLOBAL_MATRIX_OF_S.table[i]!![2].toFloat()
            if (GLOBAL_MATRIX_OF_S.table[i]!![2].toFloat() > sR) {
                sR = GLOBAL_MATRIX_OF_S.table[i]!![2].toFloat()
            }
        }
    }

    for (i in 1..GLOBAL_COUNT_ALTERNATIVE) {
        if (GLOBAL_MATRIX_OF_R.table[i]!![0].toFloat() < Rstar[0]) {
            Rstar[0] = GLOBAL_MATRIX_OF_R.table[i]!![0].toFloat()
            if (GLOBAL_MATRIX_OF_R.table[i]!![0].toFloat() < rL) {
                rL = GLOBAL_MATRIX_OF_R.table[i]!![0].toFloat()
            }
        }
        if (GLOBAL_MATRIX_OF_R.table[i]!![1].toFloat() < Rstar[1]) {
            Rstar[1] = GLOBAL_MATRIX_OF_R.table[i]!![1].toFloat()
        }
        if (GLOBAL_MATRIX_OF_R.table[i]!![2].toFloat() < Rstar[2]) {
            Rstar[2] = GLOBAL_MATRIX_OF_R.table[i]!![2].toFloat()
            if (GLOBAL_MATRIX_OF_R.table[i]!![2].toFloat() > rR) {
                rR = GLOBAL_MATRIX_OF_R.table[i]!![2].toFloat()
            }
        }
    }

    for (i in 1..GLOBAL_COUNT_ALTERNATIVE) {
        val resOfDifferenceSSPart = arrayOf(
            GLOBAL_MATRIX_OF_S.table[i]!![0].toFloat() - Sstar[2],
            GLOBAL_MATRIX_OF_S.table[i]!![1].toFloat() - Sstar[1],
            GLOBAL_MATRIX_OF_S.table[i]!![2].toFloat() - Sstar[1],
        )
        val resOfDifferenceRRPart = arrayOf(
            GLOBAL_MATRIX_OF_R.table[i]!![0].toFloat() - Rstar[2],
            GLOBAL_MATRIX_OF_R.table[i]!![1].toFloat() - Rstar[1],
            GLOBAL_MATRIX_OF_R.table[i]!![2].toFloat() - Rstar[0],
        )


        val dividerForS = sR-sL
        val resOfDividingSPart = arrayOf(
            resOfDifferenceSSPart[0]/dividerForS,
            resOfDifferenceSSPart[1]/dividerForS,
            resOfDifferenceSSPart[2]/dividerForS,
        )

        val dividerForR = rR-rL
        val resOfDividingRPart = arrayOf(
            resOfDifferenceRRPart[0]/dividerForR,
            resOfDifferenceRRPart[1]/dividerForR,
            resOfDifferenceRRPart[2]/dividerForR,
        )

        val vMultiplicationToSPart = arrayOf(
            resOfDividingSPart[0]*0.5f,
            resOfDividingSPart[1]*0.5f,
            resOfDividingSPart[2]*0.5f,
        )

        val rV = 1-GLOBAL_V
        val vMultiplicationToRPar = arrayOf(
            resOfDividingRPart[0]*rV,
            resOfDividingRPart[1]*rV,
            resOfDividingRPart[2]*rV
        )

        val result = arrayOf(
            (vMultiplicationToSPart[0]+vMultiplicationToRPar[0]).toString(),
            (vMultiplicationToSPart[1]+vMultiplicationToRPar[1]).toString(),
            (vMultiplicationToSPart[2]+vMultiplicationToRPar[2]).toString(),
        )

        GLOBAL_MATRIX_OF_Q.table[i]=result
    }

}

