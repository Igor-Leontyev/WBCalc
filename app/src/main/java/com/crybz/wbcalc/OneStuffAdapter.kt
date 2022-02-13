package com.crybz.wbcalc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crybz.wbcalc.databinding.OneStuffItemBinding

class OneStuffAdapter: RecyclerView.Adapter<OneStuffAdapter.OneStuffHolder>() {

    val staffList = ArrayList<OneStuff>()

    class OneStuffHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = OneStuffItemBinding.bind(item)

        fun bind(oneStuff: OneStuff) = with(binding){
            tvNameStaff.text = oneStuff.nameStuff
            tvCalcStaff.text = oneStuff.calculations
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneStuffHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.one_stuff_item,parent,false)
        return OneStuffHolder(view)
    }

    override fun onBindViewHolder(holder: OneStuffHolder, position: Int) {
      holder.bind(staffList[position])
    }

    override fun getItemCount(): Int {
        return staffList.size
    }
    fun addStuff(stuff: OneStuff){
        staffList.add(stuff)
        notifyDataSetChanged()
    }
}