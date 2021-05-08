package com.ozerol.budgetmanager.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ozerol.budgetmanager.R
import kotlinx.android.synthetic.main.fr_on_boarding_first.view.*

class FROnBoardingSecond : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fr_on_boarding_second, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.btnSkip.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }
}