package org.prodscan.database.tools.query

import org.prodscan.database.tools.ITable
import kotlin.reflect.KClass


/***
 * SELECT * FROM ${tableName}
 */
fun <T:Any>selectQueryGenerator(table: KClass<T>):String {
    val tableName = getTableName(table)
    return "SELECT * FROM ${tableName}"
}
fun selectQueryGenerator(table: String):String {
    return "SELECT * FROM ${table}"
}
