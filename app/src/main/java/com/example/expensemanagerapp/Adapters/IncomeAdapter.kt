package com.example.expensemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.classes.modelClasses.modelClasses.IncomeExpensesModelClass
import com.example.expensemanagerapp.R
import java.util.*

class IncomeAdapter(var list: ArrayList<IncomeExpensesModelClass>) :
    RecyclerView.Adapter<IncomeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtIncomeDate: TextView = itemView.findViewById(R.id.txtDateIncomee)
        var txtIncomeAmount: TextView = itemView.findViewById(R.id.txtIncomeAmount)
        var txtIncomeCategory: TextView = itemView.findViewById(R.id.txtCategoryIncome)
        var txtIncomeNote: TextView = itemView.findViewById(R.id.txtNoteIncome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.income_item_file, parent, false)
        return MyViewHolder(view)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtIncomeDate.text = list[position].date.toString()
        holder.txtIncomeAmount.text = list[position].amount.toString()
        holder.txtIncomeCategory.text = list[position].category
        holder.txtIncomeNote.text = list[position].note
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: ArrayList<IncomeExpensesModelClass>) {
        this.list = ArrayList()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}