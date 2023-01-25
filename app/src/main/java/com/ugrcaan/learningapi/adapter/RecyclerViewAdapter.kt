package com.ugrcaan.learningapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugrcaan.learningapi.R
import com.ugrcaan.learningapi.model.CurrencyModel
import kotlinx.android.synthetic.main.layout_currency_row.view.*

class RecyclerViewAdapter(private val currencyList: ArrayList<CurrencyModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){

        @SuppressLint("SetTextI18n")
        fun bind(currencyModel: CurrencyModel){
            itemView.currName.text = currencyModel.name
            itemView.currPrice.text = "Price: ${String.format("%.3f", currencyModel.price)}"
            itemView.currRank.text = currencyModel.rank.toString()

            Glide.with(itemView.context).load(currencyModel.logo_url).placeholder(R.drawable.ic_launcher_foreground).dontAnimate().into(itemView.currIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_currency_row, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return currencyList.count()
    }


}