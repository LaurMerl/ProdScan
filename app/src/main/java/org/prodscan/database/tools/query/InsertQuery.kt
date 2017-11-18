package org.prodscan.database.tools.query

import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

/**
 * Created by marcobedulli on 18/11/2017.
 */
fun insertQueryGenerator(table: ITable):String{

    val tableName = getTableName(table::class)

    val definitions = table::class.declaredMemberProperties
            .filter { x -> x.findAnnotation<Column>() != null      &&
                    (x.findAnnotation<Id>() == null          ||
                            x.findAnnotation<Id>()?.autoInc == false) }
            .map { x -> x.findAnnotation<Column>()?.name }
            .joinToString(separator = ", " )

    val map = hashMapOf ("TEXT" to {a:Any? -> "'${a.toString()}'"   },
            "INTEGER" to {a:Any? -> a}
    )

    val values = table::class.declaredMemberProperties
            .filter { x -> x.findAnnotation<Column>() != null      &&
                    (x.findAnnotation<Id>() == null          ||
                            x.findAnnotation<Id>()?.autoInc == false) }
            .map { x ->
                val f = map[x.findAnnotation<Column>()!!.type];
                f!!.invoke(x.getter.call(table));
            }
            .joinToString(separator = ",")

    val query = "INSERT INTO ${tableName}  ( ${definitions}  ) VALUES ( ${values} )"

    return query;

}
