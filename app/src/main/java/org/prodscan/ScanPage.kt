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
import org.prodscan.database.GetAndInsertIfNotExist
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.Product
import org.prodscan.database.model.Scan
import org.prodscan.database.model.State
import org.prodscan.database.tools.DbStatus

class ScanPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_page)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val product_code = findViewById<TextView>(R.id.ProductCodeText)
        val product_quantity = findViewById<TextView>(R.id.QuantityText)
        val exit_button = findViewById<Button>(R.id.ExitButton)

        // Inisialise an acquisition with the current timestamp and default state to IN_PROGRESS
        val acquisition = Acquisition()
        acquisition.Insert()

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

                // Create a product prototype
                val productPrototype = Product(product_code.text.toString())
                // Insert and get a new product from the product table
                val product = GetAndInsertIfNotExist(productPrototype, { c -> productPrototype.ProductCode == c.ProductCode })

                // Create a product prototype
                val scan = Scan((product_quantity.text.toString()).toInt(), product.id, acquisition)
                // Insert in the product into scan_product table
                val status = scan.Insert()

                when (status) {
                    DbStatus.FAIL -> {
                        Snackbar.make(view, "ERRORE: registrazione fallita. Riprovare.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show()
                    }

                    DbStatus.SUCCESS -> {
                        Snackbar.make(view, "${product_code.text} con ${product_quantity.text} unità registrata.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show()
                    }
                }
                product_code.setText("")
                product_quantity.setText("")
            }
        }

        exit_button.setOnClickListener {
            // Change the acquisition state to complete
            acquisition.state = State.COMPLETED
            acquisition.Update()

            val intent = Intent(this, MainPage::class.java)
            startActivity(intent);
        }
    }

}
