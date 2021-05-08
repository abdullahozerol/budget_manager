package com.ozerol.budgetmanager.home


import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.adapter.ClickWatcher
import com.ozerol.budgetmanager.adapter.ExpenseRecyclerViewAdapter
import com.ozerol.budgetmanager.database.ExpenseDatabase
import com.ozerol.budgetmanager.database.LastCurrency
import com.ozerol.budgetmanager.database.LastCurrencyDatabase
import com.ozerol.budgetmanager.database.ProfileDatabase
import com.ozerol.budgetmanager.databinding.FrHomeBinding
import com.ozerol.budgetmanager.service.Repository
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


class FRHome : Fragment() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FrHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_home, container, false)

        val app = requireNotNull(this.activity).application
        val expenseData = ExpenseDatabase.getSample(app).expenseDao
        val profileData = ProfileDatabase.getSample(app).profileDao
        val lastCurrencyData = LastCurrencyDatabase.getSample(app).lastCurrencyDao
        val repository = Repository()

        val viewModelFactory =
            FRHomeViewModelFactory(repository, expenseData, profileData, lastCurrencyData, app)
        val frHomeViewModel =
            ViewModelProvider(this, viewModelFactory).get(FRHomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.frHomeViewModel = frHomeViewModel

        val connMgr = activity
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            frHomeViewModel.getData()
        } else {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                ("İnternet bağlantınız bulunmamaktadır."),
                Snackbar.LENGTH_LONG,)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(Color.parseColor("#D4C1A1"))
                .setAction("Tamam"){
                    Toast.makeText(activity,"İnternet bağlantınızı kontrol ediniz.",Toast.LENGTH_SHORT).show()
                }
                .setActionTextColor(Color.parseColor("#99052D"))
                .show()
        }

        frHomeViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            lifecycleScope.launch {
                if (response.isSuccessful) {

                    Log.d("response: ", response.body().toString())

                    val eurToTry = response.body()?.rates?.TRY?.toDoubleOrNull()
                    val eurToUsd = response.body()?.rates?.USD?.toDoubleOrNull()
                    val eurToGbp = response.body()?.rates?.GBP?.toDoubleOrNull()

                    val df = DecimalFormat("###.##", DecimalFormatSymbols(Locale.ENGLISH))
                        .apply {
                            roundingMode = RoundingMode.HALF_UP
                        }
                    val toTry = df.format(eurToTry).toDouble()
                    val toUsd = df.format(eurToUsd).toDouble()
                    val toGbp = df.format(eurToGbp).toDouble()

                    val newCurrency = LastCurrency()
                    newCurrency.savedTl = toTry
                    newCurrency.savedSt = toGbp
                    newCurrency.savedDl = toUsd

                    lastCurrencyData.create(newCurrency)


                } else
                    Log.d("response: ", response.errorBody().toString())
            }
        })

        frHomeViewModel.toAddExpense.observe(viewLifecycleOwner, Observer { expense ->
            expense?.let {
                this.findNavController()
                    .navigate(FRHomeDirections.actionFRHomeToFRAddExpense(expense.id))
            }
        })

        frHomeViewModel.toProfile.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(FRHomeDirections.actionFRHomeToFRProfile())
            }
        })

        val adapter = ExpenseRecyclerViewAdapter(ClickWatcher { expenseId ->
            frHomeViewModel.clickDetail(expenseId)
        })
        binding.rvExpenses.adapter = adapter

        frHomeViewModel.allExpense.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        frHomeViewModel.toDetails.observe(viewLifecycleOwner, Observer { expenseId ->
            expenseId?.let {
                this.findNavController()
                    .navigate(FRHomeDirections.actionFRHomeToFRDetailExpense2(expenseId))
            }
        })

        binding.invalidateAll()

        lifecycleScope.launch {
            frHomeViewModel.selectCurrencyButton.observe(viewLifecycleOwner, Observer { value ->
                binding.invalidateAll()
                when (value) {
                    R.id.btnTl -> {
                        lifecycleScope.launch {
                            frHomeViewModel.allExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    for (i in it.indices) {
                                        it[i].cost = it[i].tlCost
                                        it[i].currency = it[i].icTl
                                    }
                                    adapter.notifyDataSetChanged()
                                    frHomeViewModel.totalExpense.observe(viewLifecycleOwner, Observer {
                                        it?.let {
                                            it.total = it.totalTl
                                            it.currency = it.icTl
                                            binding.invalidateAll()
                                        }
                                    })
                                }
                            })
                            binding.invalidateAll()
                        }
                    }
                    R.id.btnSt -> {
                        lifecycleScope.launch {
                            frHomeViewModel.allExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    for (i in it.indices) {
                                        it[i].cost = it[i].stCost
                                        it[i].currency = it[i].icSt
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            })
                            frHomeViewModel.totalExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    it.total = it.totalSt
                                    it.currency = it.icSt
                                    binding.invalidateAll()
                                }
                            })
                            binding.invalidateAll()
                        }
                    }
                    R.id.btnDl -> {
                        lifecycleScope.launch {
                            frHomeViewModel.allExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    for (i in it.indices) {
                                        it[i].cost = it[i].dlCost
                                        it[i].currency = it[i].icDl
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            })
                            frHomeViewModel.totalExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    it.total = it.totalDl
                                    it.currency = it.icDl
                                    binding.invalidateAll()
                                }
                            })
                            binding.invalidateAll()
                        }
                    }
                    R.id.btnEu -> {
                        lifecycleScope.launch {
                            frHomeViewModel.allExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    for (i in it.indices) {
                                        it[i].cost = it[i].euCost
                                        it[i].currency = it[i].icEu
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            })
                            frHomeViewModel.totalExpense.observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    it.total = it.totalEu
                                    it.currency = it.icEu
                                    binding.invalidateAll()
                                }
                            })
                            binding.invalidateAll()
                        }
                    }
                    else -> binding.invalidateAll()
                }
            })
        }
        binding.invalidateAll()

        return binding.root
    }
}