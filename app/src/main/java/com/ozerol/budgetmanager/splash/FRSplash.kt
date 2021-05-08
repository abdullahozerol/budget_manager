package com.ozerol.budgetmanager.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ozerol.budgetmanager.R


class FRSplash : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler().postDelayed({
            if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_FRSplash_to_FRHome)
            } else {
                findNavController().navigate(R.id.action_FRSplash_to_FROnBoardingViewPager)
            }

        }, 2300)

        return inflater.inflate(R.layout.fr_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}