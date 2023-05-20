package com.example.kotlinstandardapplication.PopupMenuPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstandardapplication.Adapter.LanguageAdapter
import com.example.kotlinstandardapplication.Model.Language
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.Util.Constant
import com.example.kotlinstandardapplication.Util.SharePreferenceUtil
import com.example.kotlinstandardapplication.Util.SharePreferenceUtil2
import com.example.kotlinstandardapplication.databinding.ActivityPopupMenuBinding


class PopupMenuActivity : AppCompatActivity(), LanguageAdapter.Callback {

    private lateinit var button: AppCompatButton
    private lateinit var binding: ActivityPopupMenuBinding


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popup_menu)

        setupComponent()
        setupEvent()
    }

    private fun setupComponent()
    {
        val list = ArrayList<Language>()

        val english = Language(R.string.english, Constant.LANGUAGE_EN, false)
        val german = Language(R.string.german, Constant.LANGUAGE_DE, false)
        val spanish = Language(R.string.spanish, Constant.LANGUAGE_ES, false)
        val french = Language(R.string.french, Constant.LANGUAGE_FR,false)
        val japanese = Language(R.string.japanese, Constant.LANGUAGE_JA, false)
        val portugal = Language(R.string.portugal, Constant.LANGUAGE_PT, false)
        val korean = Language(R.string.korean, Constant.LANGUAGE_KO, false)
        val vietnamese = Language(R.string.vietnamese, Constant.LANGUAGE_VI, false)

        when(SharePreferenceUtil.getDefaultLanguage(this))
        {
            Constant.LANGUAGE_EN -> english.chosen = true
            Constant.LANGUAGE_DE -> german.chosen = true
            Constant.LANGUAGE_ES -> spanish.chosen = true
            Constant.LANGUAGE_FR -> french.chosen = true
            Constant.LANGUAGE_JA -> japanese.chosen = true
            Constant.LANGUAGE_PT -> portugal.chosen = true
            Constant.LANGUAGE_KO -> korean.chosen = true
            Constant.LANGUAGE_VI -> vietnamese.chosen = true
        }

        list.add(english)
        list.add(german)
        list.add(spanish)
        list.add(french)
        list.add(japanese)
        list.add(vietnamese)
        list.add(korean)
        list.add(portugal)

        val adapter = LanguageAdapter(list, this)
        val manager = LinearLayoutManager(this)

        binding.recyclerViewLanguage.adapter = adapter
        binding.recyclerViewLanguage.layoutManager = manager
    }

    private fun setupEvent()
    {
        /*Step 1: create popup menu*/
        val popupMenu = PopupMenu(this, binding.buttonPopupMenu)
        popupMenu.inflate(R.menu.menu_popup)

        /*Step 2: set onClick event*/
        binding.buttonPopupMenu.setOnClickListener {
            popupMenu.setOnMenuItemClickListener { item->
                when(item.itemId)
                {
                    R.id.action_home ->  {
                        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_settings ->{
                        Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            try
            {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            }
            catch (e: Exception)
            {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }

        /*Step 3: */

    }

    override fun select(codeName: String) {
        SharePreferenceUtil.changeDefaultLanguage(this, codeName)
        SharePreferenceUtil2.instance?.defaultLanguage = codeName
        Toast.makeText(this, getString(R.string.change_default_language_successfully), Toast.LENGTH_SHORT).show()
        /*val intent = Intent(requireContext(), MainActivity::class.java)*/
        /*intent.putExtra(Constants.IS_CHANGE_LANGUAGE, true)*/
        /*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)*/
        /*activity?.finish()*/
        this.finish();
        startActivity(this.intent);
    }
}