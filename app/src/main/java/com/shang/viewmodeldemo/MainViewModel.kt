package com.shang.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val mJumpLiveData = MutableLiveData<Int>()
    val jumpLiveData: LiveData<Int> = mJumpLiveData

    fun jumpFragment(position: Int) {
        mJumpLiveData.value = position
    }
}