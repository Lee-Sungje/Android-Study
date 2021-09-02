package com.sungje365.fragmenttest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    val counter: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {
        viewModelScope.launch {
            counter.value = 0
        }
    }

    fun increase() {
        counter.value = counter.value?.plus(1)
    }

    fun decrease() {
        counter.value = counter.value?.minus(1)
    }
}