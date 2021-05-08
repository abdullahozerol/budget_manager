package com.ozerol.budgetmanager.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.ProfileDatabase
import com.ozerol.budgetmanager.databinding.FrProfileBinding

class FRProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FrProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_profile, container, false)

        val app = requireNotNull(this.activity).application
        val profileData = ProfileDatabase.getSample(app).profileDao

        val viewModelFactory = FRProfileViewModelFactory(profileData, app)
        val frProfileViewModel =
            ViewModelProvider(this, viewModelFactory).get(FRProfileViewModel::class.java)

        binding.lifecycleOwner = this
        binding.frProfileViewModel = frProfileViewModel

        frProfileViewModel.toHome.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(FRProfileDirections.actionFRProfileToFRHome())
            }
        })

        frProfileViewModel.showSnackBar.observe((viewLifecycleOwner), Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    ("İsim bilgisi boş bırakılamaz."),
                    Snackbar.LENGTH_LONG,
                )
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .setBackgroundTint(Color.parseColor("#D4C1A1"))
                    .setAction("Tamam") {
                        Toast.makeText(
                            activity,
                            "Lütfen isminizi giriniz.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setActionTextColor(Color.parseColor("#99052D"))
                    .show()
                frProfileViewModel.snackBarShown()
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