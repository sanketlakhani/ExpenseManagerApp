package com.example.expensemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.adapter.ExpenseAdapter
import com.example.expensemanager.classes.DBHelper
import com.example.expensemanager.classes.modelClasses.modelClasses.IncomeExpensesModelClass
import com.example.expensemanagerapp.R
import java.util.*

class ExpensesFragment : Fragment() {
    var list = ArrayList<IncomeExpensesModelClass>()
    lateinit var rcvTransaction: RecyclerView
    lateinit var dbHelper: DBHelper
    var expensesAdapter: ExpenseAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var e = inflater.inflate(R.layout.fragment_expenses, container, false)
        initView(e)
        return e
    }

    private fun initView(e:View) {
        rcvTransaction = e.findViewById(R.id.rcvExpensesTransaction)
        dbHelper = activity?.let { DBHelper(it, "ExpensesManager.db", null, 1) }!!

        list = dbHelper.expensesDisplay()
        expensesAdapter?.updateData(list)

        expensesAdapter = ExpenseAdapter(list)
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rcvTransaction.layoutManager = manager
        rcvTransaction.adapter = expensesAdapter
    }
}