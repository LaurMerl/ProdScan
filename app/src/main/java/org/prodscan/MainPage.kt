package org.prodscan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.tools.DBHelper

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main_page)
        val db =  DBHelper(this,"TestDB")
        db.createTableIfNotExist(Acquisition::class)

        Log.d("Database","Is Database open? = ${db.readableDatabase.isOpen}")
    }
}
