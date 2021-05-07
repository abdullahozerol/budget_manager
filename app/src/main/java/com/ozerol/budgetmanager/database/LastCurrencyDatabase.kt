package com.ozerol.budgetmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LastCurrency::class], version = 1, exportSchema = false)
abstract class LastCurrencyDatabase : RoomDatabase() {
    abstract val lastCurrencyDao: LastCurrencyDao

    companion object { // bir sefer oluşur
        @Volatile // RAM'de tutulacağını gösterir
        private var sampleObject: LastCurrencyDatabase? = null

        fun getSample(context : Context): LastCurrencyDatabase {
            synchronized(this){
                var sample = sampleObject
                if (sample==null){
                    sample = Room.databaseBuilder(
                        context.applicationContext,
                        LastCurrencyDatabase::class.java,
                        "currency_database"
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