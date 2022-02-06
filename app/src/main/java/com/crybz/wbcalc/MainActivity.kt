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
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    val procentNalog: Double = 7.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nameThing: String

        val btnResult = findViewById<Button>(R.id.btn_result)
        val btnDelete = findViewById<Button>(R.id.btn_delete)
        var tv_resulit = findViewById<TextView>(R.id.tv_result)
        val edPriceFirst = findViewById<EditText>(R.id.ed_priceFirst)
        val edPriceSecond = findViewById<EditText>(R.id.ed_priceSecond)
        val edNumber = findViewById<EditText>(R.id.ed_number)
        val edProcent = findViewById<EditText>(R.id.ed_procent)
        val edPriceLogistic = findViewById<EditText>(R.id.ed_priceLogistic)
        val btnSave = findViewById<ImageButton>(R.id.btn_save)



        btnResult.setOnClickListener {
            if (!isEmpty(edNumber.text.toString()) && !isEmpty(edPriceFirst.text.toString())  && !isEmpty(edPriceSecond.text.toString())
                && !isEmpty(edProcent.text.toString())  && !isEmpty(edPriceLogistic.text.toString())
            ) {

                var zacupka: Double = edPriceFirst.text.toString().toDouble() * edNumber.text.toString().toDouble()
                var priceLogistic: Double =
                    edPriceLogistic.text.toString().toDouble() * edNumber.text.toString().toDouble()
                var spin: Double = edPriceSecond.text.toString().toDouble() * edNumber.text.toString().toDouble()
                var allProc: Double = 1 - ((procentNalog + edProcent.text.toString().toDouble()) / 100)

                var fullMony: Double = ((edPriceSecond.text.toString().toDouble() * allProc)
                        - edPriceLogistic.text.toString().toDouble()
                        - edPriceFirst.text.toString().toDouble()) * edNumber.text.toString().toDouble()

                var marginality: Double = ((fullMony / spin) * 100)
                var nalog: Double = spin * (procentNalog / 100)
                var marketComis: Double = spin * (edProcent.text.toString().toDouble() / 100)
                var oneThingCosts: Double = fullMony / edNumber.text.toString().toDouble()

                tv_resulit.setText(
                    "\n  Маржинальност : ${marginality.roundToLong()}%" +
                            "\n  Оборот : ${spin.roundToLong()} руб" +
                            "\n  Прибыль c одного товара : ${oneThingCosts} руб" +
                            "\n  Общая чистая прибыль : ${fullMony.roundToLong()} руб" +
                            "\n  Комиссия маркетплейса : ${marketComis.roundToLong()} " +
                            "\n  Затраты на логистику : ${priceLogistic.roundToLong()} руб" +
                            "\n  Затраты на закупку : ${zacupka.roundToLong() } руб " +
                            "\n  Налог : ${nalog.roundToLong()} руб"
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

        btnSave.setOnClickListener() {
            if (!isEmpty(edNumber.text.toString()) &&
                !isEmpty(edPriceFirst.text.toString()) &&
                !isEmpty(edPriceSecond.text.toString()) &&
                !isEmpty(edProcent.text.toString()
                ) && !isEmpty(edPriceLogistic.text.toString())
            ) {
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

              btnSaveName.setOnClickListener() {

                  if(!isEmpty(edNameThing.text.toString())){
                      nameThing = edNameThing.text.toString()
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




