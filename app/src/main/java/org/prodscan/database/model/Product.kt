package org.prodscan.database.model

import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Table


@Table(name = "Product")
class Product (val code: Int){
    @Id(autoInc = false)
    @Column(name="id",type = "INTEGER")
    var ProductCode: Int = this.code
        get() = this.code
}