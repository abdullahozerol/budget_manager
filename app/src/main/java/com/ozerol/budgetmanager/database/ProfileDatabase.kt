package com.ozerol.budgetmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao

    companion object { // bir sefer oluşur
        @Volatile // RAM'de tutulacağını gösterir
        private var sampleObject: ProfileDatabase? = null

        fun getSample(context : Context): ProfileDatabase {
            synchronized(this){
                var sample = sampleObject
                if (sample==null){
                    sample = Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "profile_database"
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