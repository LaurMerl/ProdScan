package org.prodscan

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_scan_page.*
import org.prodscan.database.GetAndInsertIfNotExist
import org.prodscan.database.SelectAll
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.Product
import org.prodscan.database.model.Scan
import org.prodscan.database.model.State
import org.prodscan.database.tools.DbStatus
import android.widget.ArrayAdapter
import java.util.*
import kotlin.concurrent.timerTask


class ScanPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_page)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val product_code = findViewById<MultiAutoCompleteTextView>(R.id.ProductCodeText)
        val product_quantity = findViewById<TextView>(R.id.QuantityText)
        val exit_button = findViewById<Button>(R.id.ExitButton)

        // Inisialise an acquisition with the current timestamp and default state to IN_PROGRESS
        val acquisition = Acquisition()
        acquisition.Insert()

        // Checks the values of input is not null or empty
        fun checkTextValues(value:TextView): Boolean {
            if(value?.text.isNullOrEmpty()) {
                value?.setError("Campo vuoto")
                return false
            }
            return true
        }

        // Checks the quantity has lenght < 3
        fun checkQuantity(value:TextView): Boolean {
            Log.d("checkQuantity", value?.text.toString())
            if(value?.text.length > 3) {
                Toast.makeText(this, "${value?.text} supera la quantità massima consentita. Riprova.", Toast.LENGTH_LONG).show()
                value?.setText("")
                return false
            }
            return true
        }

        fun suggestions() {
            // Get all the product form the product table
            val codesList = SelectAll(Product::class).map { x -> x.ProductCode }

            val adapter = ArrayAdapter<String>(this,
                    R.layout.activity_scan_page, codesList)
            product_code.threshold = 3
            product_code.setAdapter(adapter)
            Log.d("products", codesList.size.toString())
        }

        // Get all the product form the product table
        suggestions()

        findViewById<Button>(R.id.SendButton).setOnClickListener { view ->

            // Get all the product form the product table
            suggestions()
            if(checkTextValues(product_code) && ((checkTextValues(product_quantity)) && (checkQuantity(product_quantity)))) {
                // Create a product prototype
                val productPrototype = Product(product_code.text.toString())
                // Insert and get a new product from the product table
                val product = GetAndInsertIfNotExist(productPrototype, { c -> productPrototype.ProductCode == c.ProductCode })

                // Create a product prototype
                val scan = Scan((product_quantity.text.toString()).toInt(), product.id, acquisition.id)
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

        exit_button.setOnClickListener { view ->
            // Change the acquisition state to complete
            acquisition.state = State.COMPLETED
            val result = acquisition.Update()

            when (result) {
                DbStatus.FAIL -> {
                    Snackbar.make(view, "ERRORE: acquisizione fallita. Riprova.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }

                DbStatus.SUCCESS -> {
                    Snackbar.make(view, "Acquisizione terminata con successo.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
            }

            val intent = Intent(this, MainPage::class.java)
            Timer().schedule(timerTask { startActivity(intent) }, 2000)
        }
    }

}
