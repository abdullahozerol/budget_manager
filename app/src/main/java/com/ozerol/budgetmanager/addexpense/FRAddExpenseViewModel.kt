package com.ozerol.budgetmanager.addexpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import com.ozerol.budgetmanager.database.LastCurrencyDao
import com.ozerol.budgetmanager.service.Repository
import com.ozerol.budgetmanager.service.models.Currencies
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


class FRAddExpenseViewModel(
    private val repository: Repository,
    private val expenseId: Long = 0L,
    private val expenseData: ExpenseDao,
    private val lastCurrencyData: LastCurrencyDao,
) :
    ViewModel() {


    private var job: Job? = null
    private val lastCurrencies = lastCurrencyData.getLast()

    var description: String? = null
    var cost: String? = null

    private val _toHome = MutableLiveData<Boolean?>()
    val toHome: LiveData<Boolean?>
        get() = _toHome

    private val _selectExpenseType = MutableLiveData<Int>()
    val selectExpenseType: MutableLiveData<Int>
        get() = _selectExpenseType

    init {
        _selectExpenseType.postValue(R.id.rbOther)
    }

    private val _selectCurrency = MutableLiveData<Int>()
    val selectCurrency: MutableLiveData<Int>
        get() = _selectCurrency

    init {
        _selectCurrency.postValue(R.id.rbTl)
    }

    val myResponse = MutableLiveData<Response<Currencies?>>()


    fun onAddButtonClick() {

        viewModelScope.launch {

            if(cost!=null){
                val df = DecimalFormat("###.##", DecimalFormatSymbols(Locale.ENGLISH))
                    .apply {
                        roundingMode = RoundingMode.HALF_UP
                    }

                val newExpense = Expense()
                newExpense.description = description.toString()
                newExpense.cost = cost?.toDoubleOrNull()!!

                when (this@FRAddExpenseViewModel._selectExpenseType.value) {
                    R.id.rbBill -> {
                        newExpense.imageCategory = "bill"
                    }
                    R.id.rbShopping -> {
                        newExpense.imageCategory = "shopping"
                    }
                    else -> newExpense.imageCategory = "other"
                }


                val response = repository.getData("35c35c8d7bece1ddd065206c796b1411")
                myResponse.value = response

                if (response.isSuccessful) {

                    val eurToTry = response.body()?.rates?.TRY?.toDoubleOrNull()
                    val eurToUsd = response.body()?.rates?.USD?.toDoubleOrNull()
                    val eurToGbp = response.body()?.rates?.GBP?.toDoubleOrNull()


                    when (this@FRAddExpenseViewModel._selectCurrency.value) {
                        R.id.rbTl -> {
                            newExpense.currency = " ₺"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.tlCost = cost?.toDoubleOrNull()!!

                            newExpense.euCost = df.format(cost?.toDouble()!! / eurToTry!!).toDouble()
                            newExpense.dlCost =
                                df.format((cost?.toDouble()!! / eurToTry!!) * eurToUsd!!).toDouble()
                            newExpense.stCost =
                                df.format((cost?.toDouble()!! / eurToTry!!) * eurToGbp!!).toDouble()

                        }
                        R.id.rbSt -> {
                            newExpense.currency = " £"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.stCost = cost?.toDoubleOrNull()!!

                            newExpense.euCost = df.format(cost?.toDouble()!! / eurToGbp!!).toDouble()
                            newExpense.dlCost =
                                df.format((cost?.toDouble()!! / eurToGbp!!) * eurToUsd!!).toDouble()
                            newExpense.tlCost =
                                df.format((cost?.toDouble()!! / eurToGbp!!) * eurToTry!!).toDouble()
                        }
                        R.id.rbDl -> {
                            newExpense.currency = " $"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.dlCost = cost?.toDoubleOrNull()!!

                            newExpense.euCost = df.format(cost?.toDouble()!! / eurToUsd!!).toDouble()
                            newExpense.stCost =
                                df.format((cost?.toDouble()!! / eurToUsd!!) * eurToGbp!!).toDouble()
                            newExpense.tlCost =
                                df.format((cost?.toDouble()!! / eurToUsd!!) * eurToTry!!).toDouble()
                        }
                        else -> {
                            newExpense.currency = " €"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.euCost = cost?.toDoubleOrNull()!!

                            newExpense.stCost = df.format(cost?.toDouble()!! * eurToGbp!!).toDouble()
                            newExpense.dlCost = df.format(cost?.toDouble()!! * eurToUsd!!).toDouble()
                            newExpense.tlCost = df.format(cost?.toDouble()!! * eurToTry!!).toDouble()
                        }
                    }
                } else {
                    val toTry = lastCurrencyData.getSavedTl()
                    val toUsd = lastCurrencyData.getSavedDl()
                    val toGbp = lastCurrencyData.getSavedSt()

                    when (this@FRAddExpenseViewModel._selectCurrency.value) {
                        R.id.rbTl -> {
                            newExpense.currency = " ₺"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.tlCost = cost?.toDoubleOrNull()!!

                            newExpense.euCost = df.format(cost?.toDouble()!! / toTry!!).toDouble()
                            newExpense.dlCost =
                                df.format((cost?.toDouble()!! / toTry!!) * toUsd!!).toDouble()
                            newExpense.stCost =
                                df.format((cost?.toDouble()!! / toTry!!) * toGbp!!).toDouble()

                        }
                        R.id.rbSt -> {
                            newExpense.currency = " £"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.stCost = cost?.toDoubleOrNull()!!

                            newExpense.euCost = df.format(cost?.toDouble()!! / toGbp!!).toDouble()
                            newExpense.dlCost =
                                df.format((cost?.toDouble()!! / toGbp!!) * toUsd!!).toDouble()
                            newExpense.tlCost =
                                df.format((cost?.toDouble()!! / toGbp!!) * toTry!!).toDouble()
                        }
                        R.id.rbDl -> {
                            newExpense.currency = " $"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.dlCost = cost?.toDoubleOrNull()!!

                            newExpense.euCost = df.format(cost?.toDouble()!! / toUsd!!).toDouble()
                            newExpense.stCost =
                                df.format((cost?.toDouble()!! / toUsd!!) * toGbp!!).toDouble()
                            newExpense.tlCost =
                                df.format((cost?.toDouble()!! / toUsd!!) * toTry!!).toDouble()
                        }
                        else -> {
                            newExpense.currency = " €"
                            newExpense.cost = cost?.toDoubleOrNull()!!
                            newExpense.euCost = cost?.toDoubleOrNull()!!

                            newExpense.stCost = df.format(cost?.toDouble()!! * toGbp!!).toDouble()
                            newExpense.dlCost = df.format(cost?.toDouble()!! * toUsd!!).toDouble()
                            newExpense.tlCost = df.format(cost?.toDouble()!! * toTry!!).toDouble()
                        }
                    }
                }

                newExpense.total = df.format(
                    expenseData.getTotalExpense()?.plus(newExpense.cost) ?: newExpense.cost
                ).toDouble()
                newExpense.totalTl = df.format(
                    expenseData.getTotalExpenseTl()?.plus(newExpense.tlCost) ?: newExpense.tlCost
                ).toDouble()
                newExpense.totalSt = df.format(
                    expenseData.getTotalExpenseSt()?.plus(newExpense.stCost) ?: newExpense.stCost
                ).toDouble()
                newExpense.totalEu = df.format(
                    expenseData.getTotalExpenseEu()?.plus(newExpense.euCost) ?: newExpense.euCost
                ).toDouble()
                newExpense.totalDl = df.format(
                    expenseData.getTotalExpenseDl()?.plus(newExpense.dlCost) ?: newExpense.dlCost
                ).toDouble()

                expenseData.create(newExpense)

                _toHome.value = true
            }
        }
    }


//    fun downloadCurrencyData() {
//        viewModelScope.launch {
//            val retrofit = Retrofit.Builder()
//                .baseUrl("http://data.fixer.io/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(CurrencyApi::class.java)
//
//            job = viewModelScope.launch(context = Dispatchers.IO) {
//                val response = retrofit.getData("35c35c8d7bece1ddd065206c796b1411")
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            currencies.value = it
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }

    fun clickBack() {
        _toHome.value = true
    }

}