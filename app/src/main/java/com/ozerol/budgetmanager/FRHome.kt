package com.ozerol.budgetmanager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ozerol.budgetmanager.databinding.FrHomeBinding

class FRHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FrHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fr_home,container,false)

        return binding.root
    }


}