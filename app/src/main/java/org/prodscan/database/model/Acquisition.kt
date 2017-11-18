package org.prodscan.database.model

import org.prodscan.database.model.tools.annotation.Column
import org.prodscan.database.model.tools.annotation.Id
import org.prodscan.database.model.tools.annotation.Table
import java.sql.Date


@Table(name = "Acquisition")
class Acquisition(@Column(name = "date" ,type = "Date")    val date  : Date,
                  @Column(name = "state",type = "string")  val state : State,
                  @Column(name="id",type = "INTEGER") @Id(autoInc = true) val id:Int){

}