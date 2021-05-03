package com.ozerol.budgetmanager.profile

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
import com.ozerol.budgetmanager.addexpense.FRAddExpenseDirections
import com.ozerol.budgetmanager.database.ExpenseDatabase
import com.ozerol.budgetmanager.databinding.FrProfileBinding
import com.ozerol.budgetmanager.detailexpense.FRDetailExpenseDirections

class FRProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FrProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_profile, container, false)

        val app = requireNotNull(this.activity).application
        val expenseData = ExpenseDatabase.getSample(app).expenseDao

        val viewModelFactory = FRProfileViewModelFactory(expenseData, app)
        val frProfileViewModel =
            ViewModelProvider(this, viewModelFactory).get(FRProfileViewModel::class.java)

        binding.lifecycleOwner = this
        binding.frProfileViewModel = frProfileViewModel

        frProfileViewModel.toHome.observe(viewLifecycleOwner, Observer {
            if (it==true){
                this.findNavController()
                    .navigate(FRProfileDirections.actionFRProfileToFRHome())
            }
        })

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController()
                        .navigate(FRProfileDirections.actionFRProfileToFRHome())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root

    }
}