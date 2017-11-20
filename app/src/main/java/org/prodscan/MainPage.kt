package org.prodscan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log
import org.prodscan.database.InitDatabase
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.Product
import org.prodscan.database.model.Scan
import org.prodscan.database.model.State
import org.prodscan.database.tools.DBHelper
import org.prodscan.database.tools.Database
import java.util.*

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val button_acquisition = findViewById<Button>(R.id.acqButton);

        button_acquisition.setOnClickListener {
            val intent = Intent(this, ScanPage::class.java)
            startActivity(intent);
        }

        // Initialisation on the DB
        InitDatabase(this)
    }
}
