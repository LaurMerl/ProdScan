package org.prodscan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.State
import org.prodscan.database.tools.DBHelper
import java.util.*

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        val db =  DBHelper(this,"TestDB")
        db.createTableIfNotExist(Acquisition::class)
        val c = Calendar.getInstance().time;
        db.insert(Acquisition(c,State.IN_PROGRESS,12))
        Log.d("Database","Is Database open? = ${db.readableDatabase.isOpen}")
    }
}
