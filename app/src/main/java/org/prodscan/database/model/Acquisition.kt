package org.prodscan.database.model

import org.prodscan.database.tools.ITable
import org.prodscan.database.tools.annotation.Column
import org.prodscan.database.tools.annotation.Id
import org.prodscan.database.tools.annotation.Table
import java.util.*


@Table(name = "Acquisition")
class Acquisition (val date:Date,val state : State,val id:Int): ITable {

    @Column(name = "date" ,type = "TEXT")
    var AcquisitionDate: Date = this.date
        get() = this.date

    @Column( name = "state",type = "TEXT")
    var AcquisitionState: State = this.state
        get() = this.state

    @Id(autoInc = true)
    @Column(name="id",type = "INTEGER")
    var AcquisitionId: Int = this.id
        get() = this.id

}