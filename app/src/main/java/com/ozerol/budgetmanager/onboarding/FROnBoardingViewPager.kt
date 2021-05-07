package com.ozerol.budgetmanager.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.onboarding.screens.FROnBoardingFirst
import com.ozerol.budgetmanager.onboarding.screens.FROnBoardingSecond
import com.ozerol.budgetmanager.onboarding.screens.FROnBoardingThird
import kotlinx.android.synthetic.main.fr_on_boarding_view_pager.view.*

class FROnBoardingViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fr_on_boarding_view_pager, container, false)

        val fragmenList = arrayListOf<Fragment>(
            FROnBoardingFirst(),
            FROnBoardingSecond(),
            FROnBoardingThird()
        )

        val adapter = OnBoardingViewPagerAdapter(
            fragmenList,requireActivity().supportFragmentManager,lifecycle
        )

        view.viewPager.adapter=adapter

        return view
    }


}