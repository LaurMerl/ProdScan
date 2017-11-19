package org.prodscan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

        InitDatabase(this)

        val c = Calendar.getInstance().time;
        val c1 = Scan(12,12).ScanProduct

        var acq = Acquisition(c,State.IN_PROGRESS)
        acq.Insert()
        var p = Product(123123)
        p.Insert()

        var scam = Scan(1323,p.code)
        scam.Insert()
        scam.Delete()
        scam.Update()

    }
}
