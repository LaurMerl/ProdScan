package org.prodscan.database.tools

import android.util.Log
import org.prodscan.database.tools.annotation.Relation
import org.prodscan.database.tools.annotation.Table
import org.prodscan.database.tools.query.selectQueryGenerator
import org.prodscan.database.tools.query.whereQueryGenerator
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmErasure


class RelationNtoOne<T:Any>(val type: KClass<T>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        val relInfo =  property.findAnnotation<Relation>()
        val destTable = type.findAnnotation<Table>()!!.name
        val tableName = thisRef!!::class.findAnnotation<Table>()!!.name
        val  q = selectQueryGenerator(destTable) +" "+ whereQueryGenerator(" ${tableName}.${relInfo!!.from} = ${destTable}.${relInfo!!.to} ")

        Log.d("Database",q);

        return  ArrayList();
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: List<T>) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }

}