package com.ozerol.budgetmanager.addexpense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ozerol.budgetmanager.database.ExpenseDao

class FRAddExpenseViewModel(private val expenseData: ExpenseDao, private val app: Application) : AndroidViewModel(app) {


}