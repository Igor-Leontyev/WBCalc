package com.crybz.wbcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.crybz.wbcalc.databinding.ActivityRecViewBinding

class RecView : AppCompatActivity() {

    lateinit var binding: ActivityRecViewBinding
    private val adapter = OneStuffAdapter()
    var listSaveDataCalc = mutableListOf<String>("")
    var listSaveName = mutableListOf<String>("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init() {
        binding.apply {
            recView.layoutManager = LinearLayoutManager(this@RecView)
            recView.adapter = adapter
            //for(i in 0..main.listSaveName.size - 1) {

            val stuff = OneStuff(listSaveName[0], listSaveDataCalc[0])//ПРОБЛЕМ в том что он не видет значения из маин
            adapter.addStuff(stuff)

            //  }
        }
    }
}
