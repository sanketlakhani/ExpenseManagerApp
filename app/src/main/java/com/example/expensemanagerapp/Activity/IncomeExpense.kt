package com.example.expensemanagerapp.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.example.expensemanager.activity.AllTransactionActivity
import com.example.expensemanager.adapter.MenuAdapter
import com.example.expensemanager.classes.DBHelper
import com.example.expensemanagerapp.Adapters.CatagoryAdapter
import com.example.expensemanagerapp.Models.CatagoryModelClass
import com.example.expensemanagerapp.R
import java.util.*

class IncomeExpense : AppCompatActivity() {
    lateinit var imgBack: ImageView
    lateinit var imgSaved: ImageView
    lateinit var modeSpinner: Spinner
    lateinit var edtAmount: EditText
    lateinit var edtNotes: EditText
    lateinit var txtDate: TextView
    lateinit var txtManager: TextView
    lateinit var rbtnIncome: RadioButton
    lateinit var rbtnExpense: RadioButton
    lateinit var rgroup: RadioGroup
    lateinit var txtTime: TextView
    lateinit var catagorySpinner: Spinner
    var TYPE: Int = 0
    var selectedMode: String = ""
    var categorySelected: String = " "
    var categoryList = ArrayList<CatagoryModelClass>()
    var mode = arrayOf("Cash", "Online","Check" )
    val dbHelper = DBHelper(this, "ExpensesManager.db", null, 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_expense)
        initView()
        date()
        spinner()
        category()
    }

    private fun initView() {
        imgBack = findViewById(R.id.imgBack)
        edtAmount = findViewById(R.id.edtAmount)
        edtNotes = findViewById(R.id.edtNotes)
        imgSaved = findViewById(R.id.imgSaved)
        txtManager = findViewById(R.id.txtManager)
        rbtnExpense = findViewById(R.id.rbtnExpense)
        rbtnIncome = findViewById(R.id.rbtnIncome)
        rgroup = findViewById(R.id.rgroup)
        txtTime = findViewById(R.id.txtTime)
        modeSpinner = findViewById(R.id.modeSpinner)
        catagorySpinner = findViewById(R.id.catagorySpinner)


        var id = rgroup.checkedRadioButtonId
        if (intent.hasExtra("income")) {
            txtManager.setText("ADD INCOME")
            rbtnIncome.isChecked = true
        } else if (intent.hasExtra("expenses")) {
            txtManager.setText("ADD EXPENSE")
//            rdExpensesIncome.text.toString()
            intent.getIntExtra("type", 0)
            rbtnExpense.isChecked = true
            if (intent.hasExtra("income")) {
                TYPE = 0
            } else {
                TYPE = 1
            }
        }
        imgBack.setOnClickListener {
            onBackPressed()
        }
        imgSaved.setOnClickListener {
            save()
        }
        txtTime.setOnClickListener {
            time()
        }
    }

    private fun date() {
        txtDate = findViewById(R.id.txtDate)
        val cal: Calendar = Calendar.getInstance()
        txtDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, monthOfYear, dayOfMonth ->
                    val selectedDate: String =
                        dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    txtDate.setText(selectedDate)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = cal.timeInMillis
            datePickerDialog.show()
        }
    }

    private fun category() {

        /*categoryList = dbHelper.display()
        val categoryAdapter = CatagoryAdapter(this, R.layout.category_spinner, categoryList)
        catagorySpinner.adapter = categoryAdapter*/

   /*     val mAdapter =
            com.example.expensemanager.adapter.MenuAdapter(this, R.layout.category_spinner, mode)
        modeSpinner.adapter = mAdapter*/


        if (catagorySpinner != null) {
            val adapter = CatagoryAdapter(this, R.layout.category_spinner, categoryList)
            catagorySpinner.adapter = adapter
            catagorySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        categorySelected = categoryList[position].name
//                        Log.e("TAG", "onItemSelected: ===>" + categorySelected)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
        }
        if (modeSpinner != null) {
            val adapter = MenuAdapter(this, android.R.layout.simple_spinner_dropdown_item, mode)
            modeSpinner.adapter = adapter

            modeSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        selectedMode = mode[position]
//                    println("Muscle Group selected is $selectedMode")  // <-- this works
//                        Log.e("TAG", "onItemSelected: ===>" + selectedMode)

                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
        }
    }

    private fun time() {
        val date_time = ""
        var mHour: Int
        var mMinute: Int
        val c = Calendar.getInstance()
        mHour = c[Calendar.HOUR_OF_DAY]
        mMinute = c[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->
                mHour = hourOfDay
                mMinute = minute
                txtTime.text = "$date_time $hourOfDay:$minute"
            }, mHour, mMinute, true
        )
        timePickerDialog.show()
    }

    private fun spinner() {
        categoryList = dbHelper.display()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mode)
        modeSpinner.adapter = adapter
        modeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    selectedMode = categoryList[position].name
                    Log.e("TAG", "onItemSelected: ====> $selectedMode")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun save() {
        val amount = edtAmount.text.toString()
        val notes = edtNotes.text.toString()
        val date = txtDate.text.toString()
        val time = txtTime.text.toString()
        val type = TYPE
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "Please Enter Amount", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(notes)) {
            Toast.makeText(this, "Please Enter  Note", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "Please select Date", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "Please Select Time", Toast.LENGTH_SHORT).show()
        } else {

            dbHelper.insertData(amount, categorySelected, date, selectedMode, notes, time, type)
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()

            var intent=Intent(this,AllTransactionActivity::class.java)
            startActivity(intent)

            onBackPressed()
        }
    }
}

