package org.prodscan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import org.prodscan.database.SelectAll
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.Scan
import org.prodscan.ui.DefaultAdapter
import android.content.Intent
import org.prodscan.database.model.Product


class ActivityProductList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        val myIntent = intent // gets the previously created intent

        val aid = myIntent.getLongExtra("AcquisitionId",0)
        val listView = findViewById<ListView>(R.id.productListId)
        val prodName = SelectAll(Product::class)
        val data = SelectAll(Scan::class).filter { x->x.ScanAcquisition == aid }.map { x -> Triple(x.ScanId,"${prodName.first{z->z.ProductId ==  x.ScanProduct}.ProductCode}"," ${x.ScanQuantity}") };

        val adapter = DefaultAdapter(this,data)
        listView.adapter = (adapter)
    }
}
