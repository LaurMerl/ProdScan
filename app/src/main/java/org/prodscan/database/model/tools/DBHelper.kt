package org.prodscan.database.model.tools

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.prodscan.database.model.tools.annotation.Table
import kotlin.reflect.KClass


class DBHelper: SQLiteOpenHelper {

    constructor(context : Context, databaseName:String ) : super(context,databaseName,null,1)

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + "FOO" + "("
                + "ID" + " INTEGER PRIMARY KEY," + "Name" + " TEXT,"
                + "vo" + " TEXT" + ")")

        db?.execSQL(CREATE_CONTACTS_TABLE)
        Log.d("Database","Database Inizialized")

    }

    fun <T:Any> createTableIfNotExist(tableType: KClass<T>){
        val tableName = (tableType.annotations.filter { x->x is Table }.first() as Table).name
        Log.d("Database","Creating table ${tableName}")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }





}