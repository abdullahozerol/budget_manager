package com.ozerol.budgetmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpanseDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao

    companion object { // bir sefer oluşur
        @Volatile // RAM'de tutulacağını gösterir
        private var SAMPLE_OBJECT: ExpanseDatabase? = null

        fun getSample(context : Context): ExpanseDatabase {
            synchronized(this){
                var sample = SAMPLE_OBJECT
                if (sample==null){
                    sample = Room.databaseBuilder(
                        context.applicationContext,
                        ExpanseDatabase::class.java,
                        "expense_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    SAMPLE_OBJECT = sample
                }
                return sample
            }
        }
    }
}