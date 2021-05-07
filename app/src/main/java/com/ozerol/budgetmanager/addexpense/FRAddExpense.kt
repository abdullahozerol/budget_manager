package com.ozerol.budgetmanager.addexpense

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.ExpenseDatabase
import com.ozerol.budgetmanager.database.LastCurrencyDatabase
import com.ozerol.budgetmanager.databinding.FrAddExpenseBinding
import com.ozerol.budgetmanager.service.Repository
import kotlin.math.log

class FRAddExpense : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FrAddExpenseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_add_expense, container, false)

        val args = FRAddExpenseArgs.fromBundle(requireArguments())
        val app = requireNotNull(this.activity).application
        val expenseData = ExpenseDatabase.getSample(app).expenseDao
        val lastCurrencyData=LastCurrencyDatabase.getSample(app).lastCurrencyDao
        val repository = Repository()

        val viewModelFactory = FRAddExpenseViewModelFactory(repository,args.keyexpense, expenseData, lastCurrencyData)
        val frAddExpenseViewModel =
            ViewModelProvider(this, viewModelFactory).get(FRAddExpenseViewModel::class.java)

        binding.lifecycleOwner = this
        binding.frAddExpenseViewModel = frAddExpenseViewModel

        frAddExpenseViewModel.toHome.observe(viewLifecycleOwner, Observer {
            if (it==true) {
                this.findNavController()
                    .navigate(FRAddExpenseDirections.actionFRAddExpenseToFRHome())
            }
        })

        frAddExpenseViewModel.showSnackBar.observe((viewLifecycleOwner), Observer {
            if(it==true){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    ("Açıklama ve harcama bilgileri boş bırakılamaz"),
                    Snackbar.LENGTH_LONG,)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .setBackgroundTint(Color.parseColor("#D4C1A1"))
                    .setAction("Tamam"){
                        Toast.makeText(activity,"Harcama ekleyebilmek için gerekli bilgileri giriniz.",Toast.LENGTH_SHORT).show()
                    }
                    .setActionTextColor(Color.parseColor("#99052D"))
                    .show()
                frAddExpenseViewModel.snackBarShown()
            }
        })

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController()
                        .navigate(FRAddExpenseDirections.actionFRAddExpenseToFRHome())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

//        frAddExpenseViewModel.downloadCurrencyData()

//        frAddExpenseViewModel.getData()
//        frAddExpenseViewModel.myResponse.observe(viewLifecycleOwner, Observer { response->
//            if (response.isSuccessful) {
//                Log.d("res", response.body()?.rates?.USD.toString())
//
//            }else
//                Log.d("res",response.errorBody().toString())
//        })

        return binding.root
    }
}