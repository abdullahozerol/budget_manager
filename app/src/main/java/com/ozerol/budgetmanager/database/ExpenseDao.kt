package com.ozerol.budgetmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //CRUD
interface ExpenseDao {
    @Insert
    suspend fun create(expense: Expense) //suspend: asenkron olarak çalıştırır.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAll(expense: List<Expense>)

    @Query("SELECT * FROM expense_table ORDER BY id DESC") //ters sıralama DESC
    fun readAll(): LiveData<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE id= :idNo")
    suspend fun read(idNo: Long): Expense?

    @Query("SELECT * FROM expense_table WHERE id= :idNo")
    fun readWithId(idNo: Long): LiveData<Expense?> //asenkron olarak değil normal faksiyon. Datası liveDataya atılıyor.

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

    @Query("SELECT * FROM expense_table ORDER BY id DESC LIMIT 1")
    fun getTotal(): LiveData<Expense?>

    @Query("SELECT SUM(cost) FROM expense_table")
    suspend fun getTotalExpense():Long?

    @Query("SELECT SUM(tlCost) FROM expense_table")
    suspend fun getTotalExpenseTl():Double?

    @Query("SELECT SUM(stCost) FROM expense_table")
    suspend fun getTotalExpenseSt():Double?

    @Query("SELECT SUM(euCost) FROM expense_table")
    suspend fun getTotalExpenseEu():Double?

    @Query("SELECT SUM(dlCost) FROM expense_table")
    suspend fun getTotalExpenseDl():Double?





}