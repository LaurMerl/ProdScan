package org.prodscan.database.model

import org.prodscan.database.tools.Database
import org.prodscan.database.tools.DbStatus
import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.RelationNtoOne
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Relation
import org.prodscan.database.tools.annotation.Table

@Table(name = "Scan")
data class Scan (var quantity: Int,var product: Long, var acquisition: Long): ITable {
    var id:Long = -1L;

    override fun Insert(): DbStatus {
        if(id ==-1L) {
            val nId = Database.db?.insert(this)
            if (nId != null)
                id = nId
            else{
                return DbStatus.FAIL
            }
        }else{
            throw Exception("Element already in the database, try with Update")
        }
        return DbStatus.SUCCESS    }

    override fun Update(): DbStatus {
        if(Database.db!!.update(this)>0 ) return DbStatus.SUCCESS
        return DbStatus.FAIL
    }

    override fun Delete(): DbStatus {

        DbStatus.FAIL
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor():this(quantity=-1,product = -1,acquisition = -1)

    @Column(name = "quantity" ,type = "INTEGER")
    var ScanQuantity: Int
        get() = this.quantity
        set(value){this.quantity = value}

    @Column(name = "product" ,type = "INTEGER")
    var ScanProduct: Long
        get() = this.product
        set(value){this.product = value}

    @Column(name = "acquisition" ,type = "INTEGER")
    var ScanAcquisition: Long
        get() = this.acquisition
        set(value){this.acquisition = value}


    @Id(autoInc = true)
    @Column(name="id",type = "INTEGER")
    var ScanId: Long
        get() = this.id
        set(value){this.id = value}



}