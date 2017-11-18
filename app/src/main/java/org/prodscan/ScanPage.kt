package org.prodscan

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_scan_page.*
import org.w3c.dom.Text

class ScanPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_page)
        setSupportActionBar(toolbar)

        val product_code = findViewById<TextView>(R.id.ProductCodeText)
        val product_quantity = findViewById<TextView>(R.id.QuantityText)

        fun checkTextValues(value:TextView ): Boolean {
            if(value?.text.isNullOrEmpty()) {
                value?.setError("Campo vuoto")
                return false
            }
            return true
        }

        findViewById<Button>(R.id.SendButton).setOnClickListener { view ->
            if(checkTextValues(product_code) && (checkTextValues(product_quantity))) {
                val product_code_content = product_code?.text
                val product_quantity_content = product_quantity?.text
                Snackbar.make(view, "Here we have $product_code_content for $product_quantity_content items ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

}
