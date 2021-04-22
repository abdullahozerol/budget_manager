package com.ozerol.budgetmanager

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ozerol.budgetmanager.database.ExpanseDatabase
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class) //Bunun ile çalışacak.
class DatabaseTest {
    private lateinit var expenseDao: ExpenseDao
    private lateinit var expenseDatabase: ExpanseDatabase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        expenseDatabase = Room.inMemoryDatabaseBuilder(context, ExpanseDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        expenseDao = expenseDatabase.expenseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        expenseDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun createAndReadExpense() {
        runBlocking { // Asenkron çalışması için "runBlocking altında çalışmak gerekir. Bunun için de ExpenseDao içerisinde fonsiyonları suspend yapmak gerekir. "suspend coroutine ile çalışır.
            val expense = Expense()
            expenseDao.create(expense)
            val lastCost = expenseDao.readLast()
            assertEquals(lastCost?.cost, 0.0)
        }

        // ExpenseDao da "suspend" siz olarak çalıştıracağımız zaman gerekli kod parçası.
        // suspendsiz senkron olarak çalışacağı için bu test bir defa çalışır.

//        val expense = Expense()
//        expenseDao.create(expense)
//        val lastCost = expenseDao.readLast()
//        assertEquals(lastCost?.cost,0.0)
    }
}