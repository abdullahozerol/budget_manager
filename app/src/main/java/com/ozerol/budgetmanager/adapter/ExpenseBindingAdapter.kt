package com.ozerol.budgetmanager.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ozerol.budgetmanager.database.Expense

class ExpenseBindingAdapter {

//    @BindingAdapter("icExpenseType")
//    fun ImageView.setIcExpenseType(expense: Expense){
//
//
//    }
companion object {
    @BindingAdapter("app:tvExpenseDescription")
    @JvmStatic
    fun TextView.setTvExpenseDescription(expense: Expense) {
        text = expense.description
    }


    @BindingAdapter("app:tvCost")
    @JvmStatic
    fun TextView.setTvCost(expense: Expense) {
        text = expense.cost.toString()
    }
}
}