package models

import play.api.db._
import play.api.Play.current
import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import enums.State
import org.squeryl.dsl.ManyToOne
import org.squeryl.dsl.OneToMany

case class Address(line1: String, line2: String, city: String, state: State.Value, zip: String) extends BaseEntity {
  def this() = this("","","",State.OH,"")
  
  lazy val courses: OneToMany[Course] = AppDB.addressToCourses.left(this)

}

object Address {
  def all: List[Address] = inTransaction(from(AppDB.address) { s => select(s) }.toList)

  def create(line1:String, line2: String, city: String, state: State.Value, zip: String) {
    inTransaction {
      AppDB.address.insert(new Address(line1, line2, city, state, zip))
    }
  }
  
  def delete(id: Long) {
    inTransaction {
      AppDB.address.delete(id)
    }
    
  }
  
}

