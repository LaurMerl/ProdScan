package org.prodscan.database.model

import org.prodscan.database.tools.Database
import org.prodscan.database.tools.DbStatus
import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Table


@Table(name = "Product")
data class Product (var code: String):ITable{
    var id:Long = -1

    override fun Insert(): DbStatus {
                val nId = Database.db?.insert(this)
                if (nId != null)
                    id = nId
                else{
                    return DbStatus.FAIL
                }

            return DbStatus.SUCCESS
    }

    override fun Update(): DbStatus {
        if(Database.db!!.update(this)>0 ) return DbStatus.SUCCESS
        return DbStatus.FAIL
    }

    override fun Delete(): DbStatus {
        DbStatus.FAIL
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor():this(code="-1")

    @Column(name="code",type = "TEXT")
    var ProductCode: String
        get() = this.code
        set(value){this.code = value}


    @Id(autoInc = true)
    @Column(name="id",type = "INTEGER")
    var ProductId: Long
        get() = this.id
        set(value){this.id = value}
}