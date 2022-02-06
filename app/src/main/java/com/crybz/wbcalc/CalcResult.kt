package com.crybz.wbcalc

import kotlin.math.roundToLong

class CalcResult {

    var marginality:    Long = 0
    var moneyTurnOver:  Long = 0
    var oneThingProfit: Long = 0
    var fullProfit:     Long = 0
    var mp_taxes:       Long = 0 // market place percent
    var logicsticCosts: Long = 0
    var buyCosts:       Long = 0
    var taxes:          Long = 0

    var isCalculated: Boolean = false

    fun calcState(edPriceFirst: String, edPriceSecond:   String,  edNumber: String,
                  edProcent:    String, edPriceLogistic: String)

    {
        val procentNalog: Double = 7.0

        buyCosts       = (edPriceFirst.toDouble() * edNumber.toDouble()).roundToLong()
        logicsticCosts = (edPriceLogistic.toDouble() * edNumber.toDouble()).roundToLong()

        var moneyTurnOver_d: Double =(edPriceSecond.toDouble() * edNumber.toDouble())
        moneyTurnOver  = moneyTurnOver_d.roundToLong()

        var allProc: Double = 1.0 - ((procentNalog + edProcent.toDouble()) / 100.0) // hidden calculeted

        var fullProfit_d: Double = (((edPriceSecond.toDouble() * allProc)  - edPriceLogistic.toDouble()  - edPriceFirst.toDouble()) * edNumber.toDouble())
        fullProfit     = fullProfit_d.roundToLong()

        marginality    = ((fullProfit_d / moneyTurnOver_d) * 100).roundToLong()
        taxes          = (moneyTurnOver_d * (procentNalog / 100.0)).roundToLong()
        mp_taxes       = (moneyTurnOver_d * (edProcent.toDouble() / 100.0)).roundToLong()
        oneThingProfit = fullProfit / edNumber.toDouble().roundToLong()

        isCalculated   = true
    }

}