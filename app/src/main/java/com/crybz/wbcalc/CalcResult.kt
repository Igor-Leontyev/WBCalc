package com.crybz.wbcalc

import kotlin.math.roundToLong

class CalcResult {



    var marginality: Long = 0
    var moneyTurnOver: Long = 0
    var oneThingProfit: Long = 0
    var fullProfit: Long = 0
    var mp_taxes: Long = 0 // market place percent
    var logicsticCosts: Long = 0
    var buyCosts: Long = 0
    var taxes: Long = 0

    var changeTextInMainactivity:Boolean = false
    var isZeroInput = false

    var isCalculated: Boolean = false

    fun calcState(
        edPriceFirst: String, edPriceSecond: String, edNumber: String,
        edProcent: String, edPriceLogistic: String
    ) {
        val procentNalog: Double = 7.0
        try {
            buyCosts = (checkFormatInput(edPriceFirst) * checkFormatInput(edNumber)).roundToLong()
            logicsticCosts = (checkFormatInput(edPriceLogistic) * checkFormatInput(edNumber)).roundToLong()

            var moneyTurnOver_d: Double = (checkFormatInput(edPriceSecond) * checkFormatInput(edNumber))
            moneyTurnOver = moneyTurnOver_d.roundToLong()

            var allProc: Double = 1.0 - ((procentNalog + checkFormatInput(edProcent)) / 100.0) // hidden calculeted

            var fullProfit_d: Double =
                (((checkFormatInput(edPriceSecond) * allProc) - checkFormatInput(edPriceLogistic) - checkFormatInput(
                    edPriceFirst
                )) * checkFormatInput(edNumber))
            fullProfit = fullProfit_d.roundToLong()

            marginality = ((fullProfit_d / moneyTurnOver_d) * 100).roundToLong()
            taxes = (moneyTurnOver_d * (procentNalog / 100.0)).roundToLong()
            mp_taxes = (moneyTurnOver_d * (checkFormatInput(edProcent) / 100.0)).roundToLong()
            oneThingProfit = fullProfit / checkFormatInput(edNumber).roundToLong()
        }
        catch (e: Exception){
            isZeroInput = true

        }


        isCalculated = true
    }

    fun reset() {
        isCalculated = false
    }

    fun checkFormatInput(numberInput: String): Double {
        var number: Double = 1.0
        try {
            numberInput.toDouble()
            return numberInput.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
            changeTextInMainactivity = true
           return number
        }

    }



}