package com.ozerol.budgetmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //CRUD
interface LastCurrencyDao {
    @Insert
    suspend fun create(lastCurrency: LastCurrency) //suspend: asenkron olarak çalıştırır.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAll(lastCurrency: List<LastCurrency>)

    @Query("SELECT * FROM currency_table ORDER BY id DESC") //ters sıralama DESC
    fun readAll(): LiveData<List<LastCurrency>>

    @Query("SELECT * FROM currency_table WHERE id= :idNo")
    suspend fun read(idNo: Long): LastCurrency?

    @Query("SELECT * FROM currency_table WHERE id= :idNo")
    fun readWithId(idNo: Long): LiveData<LastCurrency?> //asenkron olarak değil normal faksiyon. Datası liveDataya atılıyor.

    @Query("SELECT * FROM currency_table ORDER BY id DESC LIMIT 1")
    suspend fun readLast(): LastCurrency?

    @Query("SELECT * FROM currency_table ORDER BY id DESC LIMIT 1")
    fun getLast(): LiveData<LastCurrency?>

    @Query("SELECT savedTl FROM currency_table")
    suspend fun getSavedTl(): Double?

    @Query("SELECT savedDl FROM currency_table")
    suspend fun getSavedDl(): Double?

    @Query("SELECT savedSt FROM currency_table")
    suspend fun getSavedSt(): Double?

    @Update
    suspend fun update(lastCurrency: LastCurrency)

    @Query("DELETE FROM currency_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(lastCurrency: LastCurrency)

}