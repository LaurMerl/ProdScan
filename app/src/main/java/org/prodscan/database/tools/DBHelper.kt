package org.prodscan.database.tools

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.prodscan.database.model.State
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.query.createTableIfNotExist
import org.prodscan.database.tools.query.insertQueryGenerator
import org.prodscan.database.tools.query.selectQueryGenerator
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmErasure

class DBHelper: SQLiteOpenHelper {

    constructor(context : Context, databaseName:String ) : super(context,databaseName,null,1)

    override fun onCreate(db: SQLiteDatabase?) {
    }


    fun <T:Any> createTable(tableType: KClass<T>):DbStatus{
        try {
            this.writableDatabase.execSQL(createTableIfNotExist(tableType))

        }catch (e:SQLException){
            Log.e("Database",e.message)
            return DbStatus.FAIL
        }
       return DbStatus.SUCCESS
    }


    fun insert(table:ITable):Long{
        val inf = insertQueryGenerator(table)
        return this.writableDatabase.insert(inf.tableName,null,inf.values)
    }

    fun <T:Any>select(table:KClass<T>):List<T>{
        val q = selectQueryGenerator(table)
        val c = this.readableDatabase.rawQuery(q,null)
        if(c!=null)
           return cursorToObjList(c as Cursor,table)

        return ArrayList()
    }

    private fun <T:Any> cursorToObjList(c:Cursor,table:KClass<T>):List<T>{
        val ret:ArrayList<T> = ArrayList()
        while (c.moveToNext()){
            val cell = table.createInstance()
            table.declaredMemberProperties
                    .filter { x -> x.findAnnotation<Column>() != null }
                    .forEach { x ->
                            val i = c.getColumnIndex(x.findAnnotation<Column>()!!.name)

                            if(i==-1) throw  Exception("No column found ${x.findAnnotation<Column>()!!.name}")

                            val e = when(x.invoke(cell)) {
                                  is Date -> Date(c.getString(i))
                                  is Int  -> c.getInt(i)
                                  is String -> c.getString(i)
                                  is State ->State.valueOf(c.getString(i))
                                  is Long -> c.getLong(i)

                                 else -> throw  Exception("No type found for ${x.invoke(cell)!!::class.simpleName}")

                            }
                            if (x is KMutableProperty<*>) {
                                x.setter.call(cell, e)
                            }

                    }
            ret.add(cell)
        }
        return  ret
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}

enum class  DbStatus{
SUCCESS,FAIL
}

object  Database{
    private var _db:DBHelper? = null
    var db: DBHelper?
        get() = this._db
        set(value) { _db = value}

}