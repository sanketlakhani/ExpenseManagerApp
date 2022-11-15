package com.example.expensemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.classes.modelClasses.modelClasses.IncomeExpensesModelClass
import com.example.expensemanagerapp.R
import java.util.*

class ExpenseAdapter(var list: ArrayList<IncomeExpensesModelClass>) :
    RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>() {


    class MyViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var txtDateExpenses: TextView = ItemView.findViewById(R.id.txtDateExpenses)
        var txtExpensesAmount: TextView = ItemView.findViewById(R.id.txtExpensesAmount)
        var txtCategoryExpenses: TextView = ItemView.findViewById(R.id.txtCategoryExpenses)
        var txtNoteExpenses: TextView = ItemView.findViewById(R.id.txtNoteExpenses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.expenses_item_file, parent, false)
        return ExpenseAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseAdapter.MyViewHolder, position: Int) {
        holder.txtDateExpenses.text = list[position].date.toString()
        holder.txtExpensesAmount.text = list[position].amount.toString()
        holder.txtCategoryExpenses.text = list[position].category
        holder.txtNoteExpenses.text = list[position].note
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