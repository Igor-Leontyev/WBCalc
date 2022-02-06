package com.crybz.wbcalc

import android.app.Dialog
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameThing: String

        val btnResult = findViewById<Button>(R.id.btn_result)

        val btnDelete = findViewById<Button>(R.id.btn_delete)

        val tv_resulit = findViewById<TextView>(R.id.tv_result)

        val edPriceFirst = findViewById<EditText>(R.id.ed_priceFirst)
        val edPriceSecond = findViewById<EditText>(R.id.ed_priceSecond)
        val edNumber = findViewById<EditText>(R.id.ed_number)
        val edProcent = findViewById<EditText>(R.id.ed_procent)
        val edPriceLogistic = findViewById<EditText>(R.id.ed_priceLogistic)

        val btnSave = findViewById<ImageButton>(R.id.btn_save)

        var calcResult = CalcResult()

        var priceFirst    = ""
        var priceSecond   = ""
        var number        = ""
        var procent       = ""
        var priceLogistic = ""

         fun isFieldsFill() : Boolean {
             priceFirst    = edPriceFirst.text.toString()
             priceSecond   = edPriceSecond.text.toString()
             number        = edNumber.text.toString()
             procent       = edProcent.text.toString()
             priceLogistic = edPriceLogistic.text.toString()

            return (!isEmpty(priceFirst) && !isEmpty(priceSecond)  && !isEmpty(number)
                    && !isEmpty(procent)  && !isEmpty(priceLogistic))
        }

        /* Calc result handler */
        btnResult.setOnClickListener {
            if (isFieldsFill()) {

                calcResult.calcState(priceFirst, priceSecond, number, procent, priceLogistic)

                tv_resulit.setText(
                    "\n  Маржинальност : ${calcResult.marginality}%" +
                            "\n  Оборот : ${calcResult.moneyTurnOver} руб" +
                            "\n  Прибыль c одного товара : ${calcResult.oneThingProfit} руб" +
                            "\n  Общая чистая прибыль : ${calcResult.fullProfit} руб" +
                            "\n  Комиссия маркетплейса : ${calcResult.mp_taxes} " +
                            "\n  Затраты на логистику : ${calcResult.logicsticCosts} руб" +
                            "\n  Затраты на закупку : ${calcResult.buyCosts } руб " +
                            "\n  Налог : ${calcResult.taxes} руб"
                )
            } else {
                tv_resulit.setText(" ЗАПОЛНИТЕ ВСЕ ПОЛЯ ")
            }
        }

        btnDelete.setOnClickListener({
            edNumber.text = null
            edPriceFirst.text = null
            edProcent.text = null
            edPriceLogistic.text = null
            edPriceSecond.text = null
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
            if (isFieldsFill()) {
                var diolog = Dialog(this)
                diolog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                diolog.setContentView(R.layout.activity_diolog)
                diolog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))//прозрачный фон
                diolog.setCancelable(false)

                val edNameThing = diolog.findViewById<EditText>(R.id.ed_name_stuff)
                val tvInfo = diolog.findViewById<TextView>(R.id.tv_info_diolog)

                val btnClose = diolog.findViewById<Button>(R.id.btn_close)
                btnClose.setOnClickListener(){
                    diolog.dismiss()
                }

                val btnSaveName = diolog.findViewById<Button>(R.id.btn_save_name)

                /* Save in DataBase */
                btnSaveName.setOnClickListener() {

                  if(!isEmpty(edNameThing.text.toString())){

//                      nameThing = edNameThing.text.toString()

                      /* Create stuff entity */

                      diolog.dismiss()
                  }
                  else{
                      tvInfo.setText("введите название товара")
                  }

            }
                diolog.show()

            }
            else{
                tv_resulit.setText(" ЗАПОЛНИТЕ ВСЕ ПОЛЯ ")
            }
        }
    }
}




