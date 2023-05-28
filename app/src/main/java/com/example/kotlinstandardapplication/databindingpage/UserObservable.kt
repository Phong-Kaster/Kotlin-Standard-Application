package com.example.kotlinstandardapplication.databindingpage

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class UserObservable {
    var name = ObservableField<String>()
    var birthYear = ObservableInt()
}