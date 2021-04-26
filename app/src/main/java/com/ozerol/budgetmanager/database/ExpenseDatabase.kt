package com.ozerol.budgetmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao

    companion object { // bir sefer oluşur
        @Volatile // RAM'de tutulacağını gösterir
        private var sampleObject: ExpenseDatabase? = null

        fun getSample(context : Context): ExpenseDatabase {
            synchronized(this){
                var sample = sampleObject
                if (sample==null){
                    sample = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseDatabase::class.java,
                        "expense_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    sampleObject = sample
                }
                return sample
            }
        }
    }
}