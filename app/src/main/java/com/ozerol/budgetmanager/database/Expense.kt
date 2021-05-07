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
    var cost: Double = 0.00,
    @ColumnInfo(name = "currency")
    var currency: String = "",
    @ColumnInfo(name = "tlCost")
    var tlCost: Double = 0.00,
    @ColumnInfo(name = "stCost")
    var stCost: Double = 0.00,
    @ColumnInfo(name = "euCost")
    var euCost: Double = 0.00,
    @ColumnInfo(name = "dlCost")
    var dlCost: Double = 0.00,
    @ColumnInfo(name = "total")
    var total: Double = 0.00,
    @ColumnInfo(name = "totalTl")
    var totalTl: Double = 0.00,
    @ColumnInfo(name = "totalSt")
    var totalSt: Double = 0.00,
    @ColumnInfo(name = "totalEu")
    var totalEu: Double = 0.00,
    @ColumnInfo(name = "totalDl")
    var totalDl: Double = 0.00,
    @ColumnInfo(name = "savedTl")
    var savedTl: Double = 0.00,
    @ColumnInfo(name = "savedSt")
    var savedSt: Double = 0.00,
    @ColumnInfo(name = "tl")
    var icTl: String = " ₺",
    @ColumnInfo(name = "st")
    var icSt: String = " £",
    @ColumnInfo(name = "dl")
    var icDl: String = " $",
    @ColumnInfo(name = "eu")
    var icEu: String = " €"


)