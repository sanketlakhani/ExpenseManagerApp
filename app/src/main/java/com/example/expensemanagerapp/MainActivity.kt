package com.example.expensemanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.expensemanager.activity.AddCatagoryActivity
import com.example.expensemanager.activity.AllTransactionActivity
import com.example.expensemanagerapp.Activity.IncomeExpense

class MainActivity : AppCompatActivity() {


    lateinit var llIncome: LinearLayout
    lateinit var llExpense: LinearLayout
    lateinit var llTransaction: LinearLayout
    lateinit var llReports: LinearLayout
    lateinit var llCatagory: LinearLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {

        llIncome = findViewById(R.id.llIncome)
        llExpense = findViewById(R.id.llExpense)
        llTransaction = findViewById(R.id.llTransaction)
        llReports = findViewById(R.id.llReports)
        llCatagory = findViewById(R.id.llCatagory)

        llIncome.setOnClickListener {
            val intent = Intent(this, IncomeExpense::class.java)
            intent.putExtra("income", "ADD INCOME")
            intent.putExtra("type", 0)
            startActivity(intent)
        }
        llExpense.setOnClickListener {
            var intent = Intent(this, IncomeExpense::class.java)
            intent.putExtra("expenses", "ADD EXPENSE")
            intent.putExtra("type", 1)
            startActivity(intent)
        }
        llTransaction.setOnClickListener {
            var intent = Intent(this, AllTransactionActivity::class.java)
            startActivity(intent)
        }
        llCatagory.setOnClickListener {
            var i = Intent(this, AddCatagoryActivity::class.java)
            startActivity(i)
        }
    }
}