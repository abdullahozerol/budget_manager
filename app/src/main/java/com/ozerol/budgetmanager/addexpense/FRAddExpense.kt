package com.ozerol.budgetmanager.addexpense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.ExpenseDatabase
import com.ozerol.budgetmanager.databinding.FrAddExpenseBinding

class FRAddExpense : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FrAddExpenseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_add_expense, container, false)

        val args= FRAddExpenseArgs.fromBundle(requireArguments())
        val app = requireNotNull(this.activity).application
        val expenseData = ExpenseDatabase.getSample(app).expenseDao

        val viewModelFactory = FRAddExpenseViewModelFactory(args.keyexpense,expenseData)
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

//        frAddExpenseViewModel.toAddExpense.observe(viewLifecycleOwner, Observer { expense ->
//            expense?.let{
//                this.findNavController()
//                    .navigate(FRAddExpenseDirections.actionFRAddExpenseToFRHome())
//            }
//        })
        return binding.root
    }
}