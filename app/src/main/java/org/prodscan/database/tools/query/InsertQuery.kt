package org.prodscan.database.tools.query

import android.content.ContentValues
import org.prodscan.database.model.State
import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import java.util.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

data class InsertInfo(val tableName:String,val values:ContentValues ) {

}


fun insertQueryGenerator(table: ITable):InsertInfo{

    val tableName = getTableName(table::class)



    val map = hashMapOf ("TEXT"     to {a:Any? -> "'${a.toString()}'"   },
                                 "INTEGER" to {a:Any? -> a}
    )

    val values = table::class.declaredMemberProperties
            .filter { x -> x.findAnnotation<Column>() != null      &&
                    (x.findAnnotation<Id>() == null          ||
                            x.findAnnotation<Id>()?.autoInc == false) }
            .map { x ->
                val name = x.findAnnotation<Column>()!!.name
                Pair(name,x.getter.call(table))
            }

    val c= ContentValues()
    values.forEach({v ->
        when {
            v.second is String -> c.put(v.first,v.second as String)
            v.second is Int -> c.put(v.first,v.second as Int)
            v.second is Long -> c.put(v.first,v.second as Long)

            v.second is Date -> c.put(v.first,v.second.toString())
            v.second is State -> c.put(v.first,(v.second as State).toString())
            else -> throw  Exception("No type found for ${(v.second)!!::class.simpleName}")
        }
    })
    return InsertInfo(tableName,c)

}

