package org.prodscan

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_scan_page.*

class ScanPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_page)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val product_code = findViewById<TextView>(R.id.ProductCodeText)
        val product_quantity = findViewById<TextView>(R.id.QuantityText)
        val exit_button = findViewById<Button>(R.id.ExitButton)

        fun checkTextValues(value:TextView): Boolean {
            if(value?.text.isNullOrEmpty()) {
                value?.setError("Campo vuoto")
                return false
            }
            return true
        }

        fun checkQuantity(value:TextView): Boolean {
            Log.d("checkQuantity", value?.text.toString())
            if(value?.text.length > 3) {
                Toast.makeText(this, "${value?.text} supera la quantità massima consentita. Riprova.", Toast.LENGTH_LONG).show()
                value?.setText("")
                return false
            }
            return true
        }

        findViewById<Button>(R.id.SendButton).setOnClickListener { view ->
            if(checkTextValues(product_code) && ((checkTextValues(product_quantity)) && (checkQuantity(product_quantity)))) {
                // val product_code_content = product_code.text
                // val product_quantity_content = product_quantity.text
                // TODO add database interaction
                Snackbar.make(view, "Sent to the database: ${product_code.text} ${product_quantity.text} ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                product_code.setText("")
                product_quantity.setText("")
            }
        }

        exit_button.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent);
        }
    }

}
