package com.ozerol.budgetmanager.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ozerol.budgetmanager.database.ExpenseDao

class FRProfileViewModel (private val expenseData: ExpenseDao, private val app: Application) : AndroidViewModel(app)  {
}