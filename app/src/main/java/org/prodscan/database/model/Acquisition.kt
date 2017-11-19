package org.prodscan.database.model

import org.prodscan.database.tools.Database
import org.prodscan.database.tools.DbStatus
import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.RelationNtoOne
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Relation
import org.prodscan.database.tools.annotation.Table
import java.util.*


@Table(name = "Acquisition")
data class Acquisition (var date:Date,var state : State): ITable {
    var id:Long = -1;
    override fun Insert(): DbStatus {
        if(id==-1L) {
            val nId = Database.db?.insert(this)
            if (nId != null)
                id = nId
            else{
                return DbStatus.FAIL
            }
        }else{
            throw Exception("Element already in the database, try with Update")
        }
        return DbStatus.SUCCESS
    }

    override fun Update(): DbStatus {
        DbStatus.FAIL
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun Delete() : DbStatus{
        DbStatus.FAIL
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor():this(Calendar.getInstance().time,State.IN_PROGRESS)

    @Column(name = "date" ,type = "TEXT")
    var AcquisitionDate: Date
        get() = this.date
        set(d){this.date = d}

    @Column( name = "state",type = "TEXT")
    var AcquisitionState: State
        get() = this.state
        set(d){this.state = d}


    @Id(autoInc = true)
    @Column(name="id",type = "INTEGER")
    var AcquisitionId: Long
        get() = this.id
        set(d){this.id = d}


    @Relation(from = "id" , to="id")
    val Scans: List<Scan> by RelationNtoOne(Scan::class)
}