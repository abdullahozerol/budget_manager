package com.ozerol.budgetmanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "image_category")
    var imageCategory: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "cost")
    var cost: Long = 0L,
    @ColumnInfo(name = "currency")
    var currency: String = "",
    @ColumnInfo(name = "total_cost")
    var total: Long = 0L

)