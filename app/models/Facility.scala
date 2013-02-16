package models

import org.squeryl.KeyedEntity
import org.squeryl.dsl.ManyToOne
import org.squeryl.dsl.OneToMany

case class Facility(name: String, phone: String) extends BaseEntity {

  lazy val addresses: OneToMany[Address] = AppDB.facilityToAddresses.left(this)
  lazy val courses: OneToMany[Course] = AppDB.facilityToCourses.left(this)

}