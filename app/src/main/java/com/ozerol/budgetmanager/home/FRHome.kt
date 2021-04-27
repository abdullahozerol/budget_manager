package com.ozerol.budgetmanager.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.adapter.ClickWatcher
import com.ozerol.budgetmanager.adapter.ExpenseRecyclerViewAdapter
import com.ozerol.budgetmanager.database.ExpenseDatabase
import com.ozerol.budgetmanager.databinding.FrHomeBinding

class FRHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FrHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_home, container, false)

        val app = requireNotNull(this.activity).application
        val expenseData = ExpenseDatabase.getSample(app).expenseDao

        val viewModelFactory = FRHomeViewModelFactory(expenseData, app)
        val frHomeViewModel =
            ViewModelProvider(this, viewModelFactory).get(FRHomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.frHomeViewModel = frHomeViewModel

        frHomeViewModel.toAddExpense.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController()
                    .navigate(FRHomeDirections.actionFRHomeToFRAddExpense(1))
            }
        })

        frHomeViewModel.toProfile.observe(viewLifecycleOwner, Observer {
            if (it==true){
                this.findNavController()
                    .navigate(FRHomeDirections.actionFRHomeToFRProfile())
            }
        })

        val adapter = ExpenseRecyclerViewAdapter( ClickWatcher { expenseId ->
            Toast.makeText(context,"$expenseId", Toast.LENGTH_SHORT).show()
        } )
        binding.rvExpenses.adapter =adapter

        frHomeViewModel.allExpense.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}