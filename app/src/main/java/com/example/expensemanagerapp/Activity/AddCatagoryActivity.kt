package com.example.expensemanager.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.expensemanager.classes.DBHelper
import com.example.expensemanagerapp.R

class AddCatagoryActivity : AppCompatActivity() {
    lateinit var apbtnAddCategory: AppCompatButton
    lateinit var edtAddCategory: EditText
    val dbHelper = DBHelper(this, "ExpensesManager.db", null, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_catagory)
        initView()
    }
    private fun initView() {
        apbtnAddCategory = findViewById(R.id.btnCatagoryAdd)
        edtAddCategory = findViewById(R.id.edtCatagory)
        apbtnAddCategory.setOnClickListener {
            val category = edtAddCategory.text.toString()
            if (TextUtils.isEmpty(category)) {
                Toast.makeText(this, "Please Enter Category", Toast.LENGTH_SHORT).show()
            } else {
                dbHelper.insertCategory(category)
                edtAddCategory.setText("")
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }
}