package com.ozerol.budgetmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Insert
    suspend fun create(profile: Profile) //suspend: asenkron olarak çalıştırır.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAll(profile: List<Profile>)

    @Query("SELECT * FROM profile_table ORDER BY id DESC") //ters sıralama DESC
    fun readAll(): LiveData<List<Profile>>

    @Query("SELECT * FROM profile_table WHERE id= :idNo")
    suspend fun read(idNo: Long): Profile?

    @Query("SELECT * FROM profile_table WHERE id= :idNo")
    fun readWithId(idNo: Long): LiveData<Profile?> //asenkron olarak değil normal faksiyon. Datası liveDataya atılıyor.

    @Query("SELECT * FROM profile_table ORDER BY id DESC LIMIT 1")
    fun readLast(): LiveData<Profile?>

    @Update
    suspend fun update(profile: Profile)

    @Query("DELETE FROM profile_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(profile: Profile)

}