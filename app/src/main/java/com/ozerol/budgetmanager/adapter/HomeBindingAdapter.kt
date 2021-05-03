package com.ozerol.budgetmanager.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ozerol.budgetmanager.database.Expense

class HomeBindingAdapter {

    companion object {
        @BindingAdapter("app:tvTotalExpense")
        @JvmStatic
        fun TextView.setTvTotalExpense(expense: Expense?) {
            expense?.let {
                text = expense.total.toString()
            }
        }
    }
}