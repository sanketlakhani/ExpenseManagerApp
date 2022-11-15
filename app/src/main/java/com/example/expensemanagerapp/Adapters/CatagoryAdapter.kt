package com.example.expensemanagerapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemanagerapp.Models.CatagoryModelClass
import com.example.expensemanagerapp.R
import java.util.*

class CatagoryAdapter(

    var context: Context,
    var mode: Int,
    var categoryList: ArrayList<CatagoryModelClass>
) :
    BaseAdapter() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = inflater.inflate(R.layout.category_spinner, parent, false)
        var txtList: TextView = view.findViewById(R.id.txtCategorySpinner)
        txtList.text = (categoryList[position].name)
        return view
    }

}
