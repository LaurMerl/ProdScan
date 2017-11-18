package org.prodscan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val button_acquisition = findViewById<Button>(R.id.acqButton);

        button_acquisition.setOnClickListener {
            val intent = Intent(this, ScanPage::class.java)
            startActivity(intent);
        }
    }
}
