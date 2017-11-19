package org.prodscan.database

import android.content.Context
import org.prodscan.database.model.Acquisition
import org.prodscan.database.model.Product
import org.prodscan.database.model.Scan
import org.prodscan.database.tools.DBHelper
import org.prodscan.database.tools.Database
import kotlin.reflect.KClass


fun InitDatabase(c: Context){
    Database.db = DBHelper(c,"TestDB1")

    Database.db?.createTable(Acquisition::class)
    Database.db?.createTable(Product::class)
    Database.db?.createTable(Scan::class)
}

/***
 * Ex SelectAll(Acquisition::class)
 */
fun <T:Any>SelectAll(type:KClass<T>):List<T>{
    val res = Database.db?.select(type) ?: return ArrayList()
    return res as List<T>
}