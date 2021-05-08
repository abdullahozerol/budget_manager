package com.ozerol.budgetmanager.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.Profile
import com.ozerol.budgetmanager.database.ProfileDao
import kotlinx.coroutines.launch

class FRProfileViewModel(private val profileData: ProfileDao, private val app: Application) :
    AndroidViewModel(app) {

    var name: String? = null

    private val _toHome = MutableLiveData<Boolean?>()
    val toHome: LiveData<Boolean?>
        get() = _toHome

    private val _selectGender = MutableLiveData<Int>()
    val selectGender: MutableLiveData<Int>
        get() = _selectGender

    init {
        _selectGender.postValue(R.id.rbNoGender)
    }

    private var _showSnackBar = MutableLiveData<Boolean>()
    val showSnackBar: LiveData<Boolean>
        get() = _showSnackBar

    fun snackBarShown() {
        _showSnackBar.value = false
    }


    fun onSaveProfile() {
        viewModelScope.launch {
            if (name!=null){
                val newProfile = Profile()
                newProfile.name = name.toString()

                when (_selectGender.value) {
                    R.id.rbMale -> {
                        newProfile.gender = "male"
                    }
                    R.id.rbFemale -> {
                        newProfile.gender = "female"
                    }
                    else -> newProfile.gender = "nogender"
                }

                profileData.create(newProfile)

                _toHome.value = true
            }
            else{
                _showSnackBar.value = true
            }
        }
    }

    fun clickBack() {
        _toHome.value = true
    }
}