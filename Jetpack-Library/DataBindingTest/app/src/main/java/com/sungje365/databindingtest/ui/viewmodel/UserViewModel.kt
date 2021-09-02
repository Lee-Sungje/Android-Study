package com.sungje365.databindingtest.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val firstName: ObservableField<String> = ObservableField("First")
    val lastName: ObservableField<String> = ObservableField("Last")

    fun onClickClear() {
        firstName.set("")
        lastName.set("")
    }

    fun onClickChange() {
        firstName.set("GilDong")
        lastName.set("Hong")
    }
}