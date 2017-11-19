package org.prodscan.database.model

import org.prodscan.database.tools.Database
import org.prodscan.database.tools.DbStatus
import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Table


@Table(name = "Product")
data class Product (var code: Long):ITable{
    override fun Insert(): DbStatus {
                val nId = Database.db?.insert(this)
                if (nId != null)
                    code = nId
                else{
                    return DbStatus.FAIL
                }

            return DbStatus.SUCCESS
    }

    override fun Update(): DbStatus {
        DbStatus.FAIL
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun Delete(): DbStatus {
        DbStatus.FAIL
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor():this(code=-1)

    @Id(autoInc = false)
    @Column(name="id",type = "INTEGER")
    var ProductCode: Long
        get() = this.code
        set(value){this.code = value}
}