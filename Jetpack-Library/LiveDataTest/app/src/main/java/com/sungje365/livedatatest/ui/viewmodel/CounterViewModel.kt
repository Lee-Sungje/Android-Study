package com.sungje365.livedatatest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    val counter: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        counter.value = 0
    }

    fun increase() {
        counter.value = counter.value?.plus(1)
    }

    fun decrease() {
        counter.value = counter.value?.minus(1)
    }
}