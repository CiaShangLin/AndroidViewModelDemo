package com.shang.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AFragmentViewModel : ViewModel() {

    private val mSwitchPageLiveData = MutableLiveData<Int>()
    val switchPageLiveData: LiveData<Int> = mSwitchPageLiveData

    fun switchPage(position: Int) {
        mSwitchPageLiveData.value = position
    }
}