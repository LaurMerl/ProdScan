package org.prodscan.database.tools.query

import org.prodscan.database.tools.ITable

/***
 * WHERE what
 */
fun whereQueryGenerator(condition:String):String {
    return "WHERE ${condition}"
}