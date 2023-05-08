package com.example.kotlinstandardapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstandardapplication.Model.Language
import com.example.kotlinstandardapplication.R

/**
 * @author Phong-Apero
 * @since 04-05-2023
 * Language Adapter is an adapter for recycler view
 * @param codeNameChosen is the chosen codeName language of application.
 * For example, en - de - vn ..... is codeName
 */
class LanguageAdapter
constructor(val list: List<Language>,
            val callback: Callback): RecyclerView.Adapter<LanguageAdapter.ViewHolder>()
{

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_language, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language = list[position]


        if(language.chosen)
        {
            holder.checkIcon.visibility = View.VISIBLE
        }
        else
        {
            holder.checkIcon.visibility = View.INVISIBLE
        }

        holder.name.setText(language.name)
        holder.layout.setOnClickListener {
            callback.select(language.codeName)
            for (element in list.indices) {
                list[element].chosen = element == position
            }
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.elementName)
        val checkIcon: ImageView = itemView.findViewById(R.id.elementCheckIcon)
        val layout: LinearLayout = itemView.findViewById(R.id.elementLayout)
    }

    interface Callback{
        /**
         * @author Phong-Apero
         * @since 04-05-2023
         * select a language as default application\'s language
         *
         * we stores codeName in Shared preferences for default language application
         */
        fun select(codeName: String)
    }
}