package com.ozerol.budgetmanager.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ozerol.budgetmanager.database.Expense

class ExpenseBindingAdapter {

    @BindingAdapter("icExpenseType")
    fun ImageView.setIcExpenseType(expense: Expense){

    }

    @BindingAdapter("tvExpenseDescription")
    fun TextView.setTvExpenseDescription(expense: Expense){

    }

    @BindingAdapter("tvCost")
    fun TextView.setTvCost(expense: Expense){

    }
}