package org.prodscan.database.tools.annotation


@Retention(value = AnnotationRetention.RUNTIME)
annotation class Id(val autoInc:Boolean = false)