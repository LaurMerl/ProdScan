package org.prodscan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.MultiAutoCompleteTextView
import android.widget.ArrayAdapter
import org.prodscan.database.SelectAll
import org.prodscan.database.model.Acquisition
import org.prodscan.ui.DefaultAdapter
import android.content.Intent
import java.util.*
import kotlin.concurrent.timerTask


class AcquisitionListPage : AppCompatActivity(),AdapterView.OnItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_list)
        val listView = findViewById<ListView>(R.id.myListView)
        val data = SelectAll(Acquisition::class).map { x -> Triple(x.AcquisitionId,"${x.state}"," ${x.date}") };
        listView.onItemClickListener = this

        val adapter = DefaultAdapter(this,data)
        listView.adapter = (adapter)

    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val id =  parent!!.getItemIdAtPosition(position)
        val myIntent = Intent(this, ActivityProductList::class.java)
        myIntent.putExtra("AcquisitionId",id);
        startActivity(myIntent)
    }
}


