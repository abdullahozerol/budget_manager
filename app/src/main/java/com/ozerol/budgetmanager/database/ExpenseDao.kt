package com.ozerol.budgetmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //CRUD
interface ExpenseDao {
    @Insert
    suspend fun create(expense: Expense) //suspend: asenkron olarak çalıştırır.

    @Query("SELECT * FROM expense_table ORDER BY id DESC") //ters sıralama DESC
    fun readAll(): LiveData<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE id= :idNo")
    suspend fun read(idNo: Long): Expense?

    @Query("SELECT * FROM expense_table ORDER BY id DESC LIMIT 1")
    suspend fun readLast(): Expense?

    @Update
    suspend fun update(expense: Expense)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAll()

//    @Query("DELETE FROM expense_table WHERE id = :idNo")
//    fun delete(idNo: Long)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT SUM(cost) FROM expense_table")
    suspend fun getTotalExpense():Long?

    @Query("SELECT * FROM expense_table ORDER BY id DESC LIMIT 1")
    suspend fun getTotal():Expense?


}