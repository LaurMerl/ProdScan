package org.prodscan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.prodscan.database.InitDatabase

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val button_acquisition = findViewById<Button>(R.id.acqButton);
        val button_acquisitions = findViewById<Button>(R.id.button_acquisitions);

        button_acquisition.setOnClickListener {
            val intent = Intent(this, ScanPage::class.java)
            startActivity(intent);
        }

        button_acquisitions.setOnClickListener {
            val intent = Intent(this, AcquisitionListPage::class.java)
            startActivity(intent);
        }
        // Initialisation on the DB
        InitDatabase(this)
    }
}
