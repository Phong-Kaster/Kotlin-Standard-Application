package com.example.kotlinstandardapplication.Util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.util.Log
import java.util.Locale

class SharePreferenceUtil {
    companion object{
        /**
         * @author Phong-Apero
         * @since 04-05-2023
         * get default language
         * For example, en - de - vn ..... is codeName
         */
        fun getDefaultLanguage(context: Context): String
        {
            val sharedPreference: SharedPreferences = context.getSharedPreferences(Constant.APPLICATION_NAME, Context.MODE_PRIVATE)
            return sharedPreference.getString("defaultLanguage", Constant.LANGUAGE_EN).toString()
        }

        /**
         * @author Phong-Apero
         * @since 04-05-2023
         * set default language
         * For example, en - de - vn ..... is codeName
         */
        fun setDefaultLanguage(context: Context, codeName: String)
        {
            val sharedPreference: SharedPreferences = context.getSharedPreferences(Constant.APPLICATION_NAME,
                Context.MODE_PRIVATE
            )
            val editor = sharedPreference.edit()

            editor.putString("defaultLanguage", codeName)
            editor.apply()
        }



        /**
         * @author Phong-Apero
         * @since 04-05-2023
         * change and save default language in Share Preferences
         */
        fun changeDefaultLanguage(context: Context, language: String)
        {
            /*save in shared preferences*/
            SharePreferenceUtil.setDefaultLanguage(context, language)


            /*convert language codeName to locale-format*/
            val locale = Locale(language)
            Locale.setDefault(locale)


            /*set language for the application*/
            val config = Configuration()
            config.locale = locale
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            context.applicationContext.resources.updateConfiguration(config, context.applicationContext.resources.displayMetrics)
        }

        /**
         * @author Phong-Apero
         * @since 04-05-2023
         * get default language from Share Preference
         */
        fun getDefaultLanguageFromOS(context: Context)
        {
            val language: String = SharePreferenceUtil.getDefaultLanguage(context)
            /*Log.d(Constant.DEBUG_TAG, "getDefaultLanguage: $language")*/
            if (language == "") {
                val config = Configuration()
                val locale = Locale.getDefault()
                Locale.setDefault(locale)
                config.locale = locale
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
            }
            else
            {
                if (language == Constant.LANGUAGE_EN){
                    changeDefaultLanguage(context, Constant.LANGUAGE_EN)
                }
                else {
                    changeDefaultLanguage(context, language)
                }
            }
        }/*end fun getDefaultLanguage*/
    }
}