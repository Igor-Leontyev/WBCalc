package com.crybz.wbcalc

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {


    var recView = RecView()

    var calcResultForSave:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val btnResult = findViewById<Button>(R.id.btn_result)

        val btnDelete = findViewById<Button>(R.id.btn_delete)
        val btnNotes = findViewById<ImageButton>(R.id.btn_notes)

        val tv_resulit = findViewById<TextView>(R.id.tv_result)

        val edPriceFirst = findViewById<EditText>(R.id.ed_priceFirst)
        val edPriceSecond = findViewById<EditText>(R.id.ed_priceSecond)
        val edNumber = findViewById<EditText>(R.id.ed_number)
        val edProcent = findViewById<EditText>(R.id.ed_procent)
        val edPriceLogistic = findViewById<EditText>(R.id.ed_priceLogistic)

        val btnSave = findViewById<ImageButton>(R.id.btn_save)

        var calcResult = CalcResult()

        var priceFirst = ""
        var priceSecond = ""
        var number = ""
        var procent = ""
        var priceLogistic = ""

        fun isFieldsFilled(): Boolean {
            priceFirst = edPriceFirst.text.toString()
            priceSecond = edPriceSecond.text.toString()
            number = edNumber.text.toString()
            procent = edProcent.text.toString()
            priceLogistic = edPriceLogistic.text.toString()

            return (!isEmpty(priceFirst) && !isEmpty(priceSecond) && !isEmpty(number)
                    && !isEmpty(procent) && !isEmpty(priceLogistic))
        }

        btnNotes.setOnClickListener() {
            recView.listSaveName.add("jhlkjhkj")//edNameThing.text.toString()
            recView.listSaveDataCalc.add("KKJMLLKJLKJ")//calcResultForSave
            val intent = Intent(this, recView::class.java)
// start your next activity
            startActivity(intent)
        }

        /* Calc result handler */
        /**//**//**//**///-**/--/////***
        btnResult.setOnClickListener {
            if (isFieldsFilled()) {
                if (!calcResult.isCalculated) {
                    calcResult.calcState(priceFirst, priceSecond, number, procent, priceLogistic)
                    calcResult.isCalculated = false
                }


                if (calcResult.changeTextInMainactivity || calcResult.isZeroInput) {
                    if (calcResult.isZeroInput) {
                        tv_resulit.setText("???????????????????? ???????????? ???? ?????????? ???????? ???????? '0'")
                        calcResult.isZeroInput = false
                    } else {
                        tv_resulit.setText(" ?????????????? ???????????????????????? ?????????????? ?????? ???????????????? ")
                        calcResult.changeTextInMainactivity = false

                    }


                } else if (!calcResult.changeTextInMainactivity) {
                    tv_resulit.setText(
                        "\n  ?????????????????????????? : ${calcResult.marginality}%" +
                                "\n  ???????????? : ${calcResult.moneyTurnOver} ??????" +
                                "\n  ?????????????? c ???????????? ???????????? : ${calcResult.oneThingProfit} ??????" +
                                "\n  ?????????? ???????????? ?????????????? : ${calcResult.fullProfit} ??????" +
                                "\n  ???????????????? ???????????????????????? : ${calcResult.mp_taxes} " +
                                "\n  ?????????????? ???? ?????????????????? : ${calcResult.logicsticCosts} ??????" +
                                "\n  ?????????????? ???? ?????????????? : ${calcResult.buyCosts} ?????? " +
                                "\n  ?????????? : ${calcResult.taxes} ??????"
                    )
                    calcResultForSave = tv_resulit.text.toString()
                }
            } else {
                tv_resulit.setText(" ?????????????????? ?????? ???????? ")
            }
        }



        btnDelete.setOnClickListener({
            edNumber.text = null
            edPriceFirst.text = null
            edProcent.text = null
            edPriceLogistic.text = null
            edPriceSecond.text = null

            calcResult.reset()

            tv_resulit.setText("")
        })

        //                      calcResult.marginality = marginality.roundToLong()
//                      calcResult.moneyTurnOver = spin.roundToLong()
//                      calcResult.oneThingProfit = oneThingCosts.roundToLong()
//                      calcResult.fullProfit = fullMony.roundToLong()
//                      calcResult.mp_taxes = marketComis.roundToLong()
//                      calcResult.logicsticCosts = priceLogistic.roundToLong()
//                      calcResult.buyCosts = zacupka.roundToLong()
//                      calcResult.taxes = nalog.roundToLong()

        /* Open saving dialog */
        btnSave.setOnClickListener() {
            if (isFieldsFilled()) {

                var diolog = Dialog(this)
                diolog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                diolog.setContentView(R.layout.activity_diolog)
                diolog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))//???????????????????? ??????
                diolog.setCancelable(false)

                val edNameThing = diolog.findViewById<EditText>(R.id.ed_name_stuff)
                val tvInfo = diolog.findViewById<TextView>(R.id.tv_info_diolog)

                val btnClose = diolog.findViewById<Button>(R.id.btn_close)
                btnClose.setOnClickListener() {
                    diolog.dismiss()
                }


                val btnSaveName = diolog.findViewById<Button>(R.id.btn_save_name)

                /* Save in DataBase */
                btnSaveName.setOnClickListener() {

                    if (!isEmpty(edNameThing.text.toString())) {

                        if (!calcResult.isCalculated) {
                            calcResult.calcState(priceFirst, priceSecond, number, procent, priceLogistic)//???????????? ?????????????? ?????????? ?? ???????? ????????????
                            calcResult.isCalculated = false

                        }

                        /* Create stuff entity */

                        diolog.dismiss()
                    } else {
                        tvInfo.setText("?????????????? ???????????????? ????????????")
                    }

                }
                diolog.show()
            } else {
                tv_resulit.setText(" ?????????????????? ?????? ???????? ")
            }
        }
    }
}




