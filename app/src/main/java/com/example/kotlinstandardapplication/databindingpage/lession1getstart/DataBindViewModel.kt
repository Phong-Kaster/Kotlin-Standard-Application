package com.example.kotlinstandardapplication.databindingpage.lession1getstart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindViewModel
constructor(private val callback: Callback) : ViewModel() {
    var user: MutableLiveData<User> = MutableLiveData()
    var text: MutableLiveData<String> = MutableLiveData()


    init {
        val defaultIdentity = User("PhongKaster", 2000)
        user.postValue(defaultIdentity)
        text.postValue("This is the sate BEFORE clicking button")
    }

    fun changeIdentity() {
        val newIdentity = User("NguyenThanhPhong", 2023)
        user.postValue(newIdentity)
        callback.showToast()
    }

    fun changeText() {
        text.postValue("This is the state AFTER clicking button")
    }

    interface Callback {
        fun showToast()
    }
}