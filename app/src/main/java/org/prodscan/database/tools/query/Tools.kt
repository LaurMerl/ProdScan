package org.prodscan.database.tools.query

import org.prodscan.database.tools.annotation.Table
import kotlin.reflect.KClass


fun <T:Any> getTableName(tableType: KClass<T>):String{
    return (tableType.annotations.filter { x->x is Table }.first() as Table).name
}