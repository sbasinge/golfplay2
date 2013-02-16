package models

import play.api.db._
import play.api.Play.current
import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import enums.State
import org.squeryl.dsl.ManyToOne

case class Address(line1: String, line2: String, city: String, state: State.Value, zip: String, facilityId: Long) extends BaseEntity {
  def this() = this("","","",State.OH,"",0)
  
  lazy val facility: ManyToOne[Facility] = AppDB.facilityToAddresses.right(this)

}

object Address {
  def all: List[Address] = inTransaction(from(AppDB.addressTable) { s => select(s) }.toList)

  def create(line1:String, line2: String, city: String, state: State.Value, zip: String, facilityId: Long) {
    inTransaction {
      AppDB.addressTable.insert(new Address(line1, line2, city, state, zip, facilityId))
    }
  }
  
  def delete(id: Long) {
    inTransaction {
      AppDB.addressTable.delete(id)
    }
    
  }
  
}

