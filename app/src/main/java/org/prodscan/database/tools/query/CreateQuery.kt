package org.prodscan.database.tools.query

import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

fun <T:Any> createTableIfNotExist(tableType: KClass<T>):String{
        val tableName = getTableName(tableType)

        val definitions = tableType.declaredMemberProperties
                .filter { x -> x.findAnnotation<Column>() != null }
                .map{ x ->convertToColumnDefinition(x) }
                .joinToString (",")


        val query = "CREATE TABLE IF NOT EXISTS ${tableName} ( ${definitions} )"
        return query;
    }



private fun <T>convertToColumnDefinition(property: KProperty1<T, *>):String{

    val columnAnnotation = property.findAnnotation<Column>()

    val idAnnotation:String = property.findAnnotation<Id>().let { x ->
        if(x!=null && x.autoInc){
            "PRIMARY KEY AUTOINCREMENT"
        }else if (x!=null) {
            "PRIMARY KEY"
        }else {
            ""
        }
    }

    return "${columnAnnotation!!.name} ${columnAnnotation!!.type} ${idAnnotation}"
}

