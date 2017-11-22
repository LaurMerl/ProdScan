package org.prodscan.ui

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.prodscan.R
import android.widget.TextView


  class DefaultAdapter: BaseAdapter {

    val datas:ArrayList<Triple<Long,String,String>> = ArrayList();
    val activity: Activity
    val inflater : LayoutInflater

     constructor (a: Activity, d:List<Triple<Long,String,String>>):super() {
        activity = a;
         datas.addAll(d);
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vi = convertView
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_field, null)



        val title = vi!!.findViewById<TextView>(R.id.DateId) // title
        val artist = vi!!.findViewById<TextView>(R.id.StatusId)

        val d = datas[position]

        title.text = d.second
        artist.text = d.third
        return  vi;
    }

    override fun getItem(position: Int): Any {
        return datas[position]
    }

    override fun getItemId(position: Int): Long {
        return datas[position].first.toLong()

    }

    override fun getCount(): Int {
        return datas.size;
    }

}