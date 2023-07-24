package com.example.kotlinstandardapplication.page.databindingpage.lession1getstart

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class UserObservable {
    var name = ObservableField<String>()
    var birthYear = ObservableInt()
}