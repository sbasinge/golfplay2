package models

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._

object AppDB extends Schema {
  val address = table[Address]
  val course = table[Course]

  val addressToCourses =
    oneToManyRelation(address, course).
      via((address, course) => address.id === course.addressId)

      
}