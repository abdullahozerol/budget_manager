package com.ozerol.budgetmanager.detailexpense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.ExpenseDatabase
import com.ozerol.budgetmanager.databinding.FrDetailExpenseBinding

class FRDetailExpense : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FrDetailExpenseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_detail_expense, container, false)

        val args = FRDetailExpenseArgs.fromBundle(requireArguments())
        val app = requireNotNull(this.activity).application
        val expenseData = ExpenseDatabase.getSample(app).expenseDao

        val frDetailExpenseViewModelFactory =
            FRDetailExpenseViewModelFactory(args.keyexpense, expenseData)
        val frDetailExpenseViewModel = ViewModelProvider(
            this,
            frDetailExpenseViewModelFactory
        ).get(FRDetailExpenseViewModel::class.java)

        binding.lifecycleOwner = this
        binding.frDetailExpenseViewModel = frDetailExpenseViewModel

        frDetailExpenseViewModel.toHome.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(FRDetailExpenseDirections.actionFRDetailExpenseToFRHome())
            }
        })

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController()
                        .navigate(FRDetailExpenseDirections.actionFRDetailExpenseToFRHome())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }
}