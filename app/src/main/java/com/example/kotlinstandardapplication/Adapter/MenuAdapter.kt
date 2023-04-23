package com.example.kotlinstandardapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import com.example.kotlinstandardapplication.Model.Menu
import com.example.kotlinstandardapplication.R

class MenuAdapter constructor(val context: Context,private val list: List<Menu>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].icon.toLong()
    }


    override fun getView(position : Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ItemHolder
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null)
        {
            view = inflater.inflate(R.layout.spinner_element_menu, parent, false)
            viewHolder = ItemHolder(view)
            view?.tag = viewHolder
        }
        else
        {
            view = convertView
            viewHolder = view.tag as ItemHolder
        }
        viewHolder.name.text = list[position].name

        val iconValue = list[position].icon
        viewHolder.icon.setImageResource(iconValue)

        return view
    }



    inner class ItemHolder(row: View?) {
        val name: TextView
        val icon: ImageView

        init {
            icon = row?.findViewById(R.id.elementIcon) as ImageView
            name = row.findViewById(R.id.elementName) as TextView
        }
    }

}