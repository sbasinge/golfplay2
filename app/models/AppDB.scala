package models

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._

object AppDB extends Schema {
  val addressTable = table[Address]
  val facilities = table[Facility]
  val courses = table[Course]

  val facilityToAddresses =
    oneToManyRelation(facilities, addressTable).
      via((facility, address) => facility.id === address.facilityId)

  val facilityToCourses =
    oneToManyRelation(facilities, courses).
      via((facility, course) => facility.id === course.facilityId)
      
}