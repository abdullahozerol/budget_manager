package com.ozerol.budgetmanager.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingViewPagerAdapter(
    list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fm, lifecycle) {
    val fragmenlist = list
    override fun getItemCount(): Int {
        return fragmenlist.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmenlist[position]
    }
}