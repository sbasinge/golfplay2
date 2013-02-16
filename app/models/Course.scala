package models

import org.squeryl.dsl.ManyToOne

case class Course(name: String, facilityId: Long) extends BaseEntity {
  lazy val facility: ManyToOne[Facility] = AppDB.facilityToCourses.right(this)

}