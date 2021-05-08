package com.ozerol.budgetmanager.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ozerol.budgetmanager.R
import kotlinx.android.synthetic.main.fr_on_boarding_third.view.*

class FROnBoardingThird : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fr_on_boarding_third, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.btnFinish.setOnClickListener {
            findNavController().navigate(R.id.action_FROnBoardingViewPager_to_FRHome)
            onBoardingFinish()
        }

        return view
    }

    private fun onBoardingFinish() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}
