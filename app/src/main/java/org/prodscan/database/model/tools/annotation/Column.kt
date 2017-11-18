package org.prodscan.database.model.tools.annotation


@Target(AnnotationTarget.FIELD)
annotation class Column(val name:String, val type: String)