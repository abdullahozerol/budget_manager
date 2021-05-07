package com.ozerol.budgetmanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class LastCurrency(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "savedTl")
    var savedTl: Double = 0.00,
    @ColumnInfo(name = "savedSt")
    var savedSt: Double = 0.00,
    @ColumnInfo(name = "savedDl")
    var savedDl: Double = 0.00

)