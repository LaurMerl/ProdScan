package org.prodscan.database.tools.annotation

@Retention(value = AnnotationRetention.RUNTIME)
annotation class Relation(val from:String, val to:String)
