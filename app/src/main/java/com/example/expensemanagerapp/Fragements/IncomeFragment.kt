package com.example.expensemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.adapter.IncomeAdapter
import com.example.expensemanager.classes.DBHelper
import com.example.expensemanager.classes.modelClasses.modelClasses.IncomeExpensesModelClass
import com.example.expensemanagerapp.R
import java.util.*

class IncomeFragment : Fragment() {
    var list = ArrayList<IncomeExpensesModelClass>()
    lateinit var rcvIncome: RecyclerView
    lateinit var dHelper: DBHelper
    var incomeAdapter: IncomeAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var i = inflater.inflate(R.layout.fragment_income, container, false)
        initView(i)
        return i
    }

    private fun initView(i: View) {
        rcvIncome = i.findViewById(R.id.rcvIncomeTransaction)
        dHelper = activity?.let { DBHelper(it, "ExpensesManager.db", null, 1) }!!

        list = dHelper.incomeDisplay()
        incomeAdapter?.updateData(list)

        incomeAdapter = IncomeAdapter(list)
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rcvIncome.layoutManager = manager
        rcvIncome.adapter = incomeAdapter
    }
}
