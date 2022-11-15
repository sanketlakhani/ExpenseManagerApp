package com.example.expensemanager.classes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.expensemanager.classes.modelClasses.modelClasses.IncomeExpensesModelClass
import com.example.expensemanagerapp.Models.CatagoryModelClass
import java.util.*

class DBHelper(
    var context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    var version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sQL =
            "create table IncomeExpensesTB (id Integer primary key autoincrement, AMOUNT integer,CATEGORY text,DATE integer,MODE text,TIME integer, NOTE text,TYPE integer)"
        db?.execSQL(sQL)
        val cSql = "create table AddCategoryTB(id integer primary key autoincrement, category text)"
        db?.execSQL(cSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(
        amount: String,
        category: String,
        date: String,
        selectedMode: String,
        time: String,
        notes: String,
        type: Int,
    ) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("AMOUNT", amount)
        cv.put("CATEGORY", category)
        cv.put("DATE", date)
        cv.put("MODE", selectedMode)
        cv.put("NOTE", notes)
        cv.put("TIME", time)
        cv.put("TYPE", type)
        db.insert("IncomeExpensesTB", null, cv)
//        Log.e("TAG", "insertData: ---- >" + success)
    }

    fun insertCategory(category: String) {
        val db = writableDatabase
        val c = ContentValues()
        c.put("category", category)
        val rs = db.insert("AddCategoryTB", null, c)
//        Log.e("TAG", "insertCategory:====Added " + rs)
    }

    fun display(): ArrayList<CatagoryModelClass> {
        val list = ArrayList<CatagoryModelClass>()
        val db = readableDatabase
        val sql = "select * from AddCategoryTB"
        val cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        do {
            var id = cursor.getInt(0)
            var category = cursor.getString(1)
            list.add(CatagoryModelClass(id, category))
            Log.e("TAG", "display: ===" + category)
        } while (cursor.moveToNext())
        return list
    }
    fun incomeDisplay(): ArrayList<IncomeExpensesModelClass> {
        var list = ArrayList<IncomeExpensesModelClass>()
        var db = readableDatabase
        var sql = "select * from IncomeExpensesTB where Type=0"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        do {
            var id = cursor.getInt(0)
            var amount = cursor.getInt(1)
            var category = cursor.getString(2)
            var date = cursor.getString(3)
            var mode = cursor.getString(4)
            list.add(IncomeExpensesModelClass(id, amount, category, date, mode))
//            Log.e("TAG", "displayIncomeExpense:display ===>"+Id+"  "+Amount+"   "+Category+"   "+Date+"   "+Mode+"   "+Note+"   "+Type)
        } while (cursor.moveToNext())
        return list
    }

    fun expensesDisplay(): ArrayList<IncomeExpensesModelClass> {
        var list = ArrayList<IncomeExpensesModelClass>()
        var db = readableDatabase
        var sql2 = "select * from IncomeExpensesTB where Type=1"
        var cursor: Cursor = db.rawQuery(sql2, null)
        cursor.moveToFirst()
        do {
            val id = cursor.getInt(0)
            var amount = cursor.getInt(1)
            var category = cursor.getString(2)
            var date = cursor.getString(3)
            var mode = cursor.getString(4)
//            var note = cursor.getString(5)
//            val time = cursor.getInt(6)
//            var type = cursor.getInt(7)
            list.add(IncomeExpensesModelClass(id, amount, category, date, mode))
            Log.e(
                "TAG",
                "displayIncomeExpense:display ===>" + id + "  " + amount + "   " + category + "   " + date + "   " + mode + "   "
            )
        } while (cursor.moveToNext())
        return list
    }
}