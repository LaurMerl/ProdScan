package org.prodscan.database.tools.annotation


@Retention(value = AnnotationRetention.RUNTIME)
annotation class Column(val name:String, val type: String)