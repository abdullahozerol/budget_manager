package com.ozerol.budgetmanager.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.Profile

class BindingAdapter {


    companion object {
        @BindingAdapter("imExpenseType")
        @JvmStatic
        fun ImageView.setImExpenseType(expense: Expense?) {
            expense?.let {
                setImageResource(
                    when (expense.imageCategory) {
                        "bill" -> R.drawable.ic_bill
                        "shopping" -> R.drawable.ic_shopping
                        else -> R.drawable.ic_others
                    }
                )
            }
        }

        @BindingAdapter("app:tvExpenseDescription")
        @JvmStatic
        fun TextView.setTvExpenseDescription(expense: Expense?) {
            expense?.let {
                text = expense.description
            }
        }


        @BindingAdapter("app:tvCost")
        @JvmStatic
        fun TextView.setTvCost(expense: Expense?) {
            expense?.let {
                text = expense.cost.toString()
            }
        }

        @BindingAdapter("app:tvTotalExpense")
        @JvmStatic
        fun TextView.setTvTotalExpense(expense: Expense?) {
            expense?.let {
                text = expense.total.toString()
            }
        }

        @BindingAdapter("app:tvName")
        @JvmStatic
        fun TextView.setTvName(profile: Profile?) {
            profile?.let {
                when (profile.gender) {
                    "male" -> text = profile.name + " Bey"
                    "female" -> text = profile.name + " Hanım"
                    else -> text = profile.name
                }
            }
        }


    }
}