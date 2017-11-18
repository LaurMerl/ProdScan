package org.prodscan.database.model

import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Table

@Table(name = "Scan")
class Scan (val quantity: Int, val id:Int): ITable {


    @Column(name = "quantity" ,type = "INTEGER")
    var ScanQuantity: Int = this.quantity
        get() = this.quantity

    @Id(autoInc = true)
    @Column(name="id",type = "INTEGER")
    var ScanId: Int = this.id
        get() = this.id

    var ScanProduct: List<Product> = ArrayList<Product>()
        get() = ArrayList<Product>()


}