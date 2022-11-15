package com.example.expensemanager.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.expensemanager.adapter.ViewPagerAdapter
import com.example.expensemanagerapp.R
import com.google.android.material.tabs.TabLayout
import java.util.*

class AllTransactionActivity : AppCompatActivity() {
   // lateinit var imgBack: ImageView
    lateinit var imgFilter: ImageView
    lateinit var tabTransaction: TabLayout
    lateinit var viewPagerTransaction: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_transaction)
        initView()
    }

    private fun initView() {
        tabTransaction = findViewById(R.id.tabLayout)

        viewPagerTransaction = findViewById(R.id.viewPager)
        tabTransaction.addTab(tabTransaction.newTab().setText("INCOMES"))
        tabTransaction.addTab(tabTransaction.newTab().setText("EXPENSES"))
        val pagerAdapter = ViewPagerAdapter(this, supportFragmentManager, tabTransaction.tabCount)
        viewPagerTransaction.adapter = pagerAdapter
        viewPagerTransaction.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabTransaction
            )
        )
        tabTransaction.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPagerTransaction.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun date() {
        val cal: Calendar = Calendar.getInstance()
        imgFilter.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, monthOfYear, dayOfMonth ->
                    val selectedDate: String =
                        dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = cal.timeInMillis
            datePickerDialog.show()
        }
    }

}