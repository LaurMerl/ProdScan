package org.prodscan.database.tools

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.prodscan.database.tools.query.createTableIfNotExist
import org.prodscan.database.tools.query.insertQueryGenerator
import kotlin.reflect.KClass

class DBHelper: SQLiteOpenHelper {

    constructor(context : Context, databaseName:String ) : super(context,databaseName,null,1)

    override fun onCreate(db: SQLiteDatabase?) {
    }


    fun <T:Any> createTable(tableType: KClass<T>){
        this.writableDatabase.execSQL(createTableIfNotExist(tableType))
    }


    fun insert(table:ITable){
        this.writableDatabase.execSQL(insertQueryGenerator(table))
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}