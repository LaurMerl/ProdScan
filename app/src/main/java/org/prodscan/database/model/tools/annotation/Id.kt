package org.prodscan.database.model.tools.annotation


@Target(AnnotationTarget.FIELD)
annotation class Id(val autoInc:Boolean = false)