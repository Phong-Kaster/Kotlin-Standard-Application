package com.example.kotlinstandardapplication.Util

import android.content.Context
import android.content.SharedPreferences

class SharePreferenceUtil2
constructor(context: Context)
{
    private var sharePreferenceInstance: SharedPreferences? = null

    init {
        sharePreferenceInstance =
            context.getSharedPreferences(Constant.APPLICATION_NAME, Context.MODE_PRIVATE)
    }

    companion object{
        var instance: SharePreferenceUtil2? = null
            get() = field ?: throw NullPointerException("PrefUtils is not initialized")

        @Synchronized
        fun initialize(context: Context) {
            instance = SharePreferenceUtil2(context)
        }
    }

    var defaultLanguage: String
        get() = sharePreferenceInstance?.getString(Constant.DEFAULT_LANGUAGE, Constant.LANGUAGE_EN)
            ?: Constant.LANGUAGE_EN
        set(value) {
            sharePreferenceInstance?.edit()?.putString(Constant.DEFAULT_LANGUAGE, value)?.apply()
        }
}